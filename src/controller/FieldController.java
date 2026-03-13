package controller;

import field.SeedField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import model.Game;
import model.Money;
import model.SpriteLib;

import java.util.Objects;

import static controller.SeedController.feed;

public class FieldController {
    public static void setMouseEffect(Game game, int fieldId) {
        Polygon top = (Polygon) game.groups[fieldId].lookup("#Top_Tile_" + fieldId);
        Polygon right = (Polygon) game.groups[fieldId].lookup("#Right_Tile_" + fieldId);
        Polygon left = (Polygon) game.groups[fieldId].lookup("#Left_Tile_" + fieldId);
        SeedField seedField = game.seedFields[fieldId];

        top.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                if (!seedField.isUnlock) {
                    if (seedField.buyField(game))
                    {
                        seedField.isUnlock = true;
                        game.getPlayer().addField(seedField);
                        top.setFill(Color.GREEN);
                        top.setOpacity(1);
                        right.setFill(Color.BROWN);
                        right.setOpacity(1);
                        left.setFill(Color.BROWN);
                        left.setOpacity(1);
                    }
                } else if (seedField.harvest) {
                    int quantity = game.getPlayer().resourceMap.get(seedField.getActualSeed()).getQuantity();
                    game.getPlayer().resourceMap.get(seedField.getActualSeed()).setQuantity(quantity + 3);
                    game.seedFields[fieldId].setReady(true);
                    top.setFill(Color.GREEN);
                    seedField.harvest = false;
                } else if (Objects.equals(seedField.state, "Building")) {
                    int amount = game.getPlayer().getMoney();
                    game.getPlayer().setMoney(amount + seedField.generatedMoney);
                    seedField.generatedMoney = 0;
                    Money.createMoney(game);
                } else if (seedField.animalRend) {
                    String animal = seedField.getActualAnimal();
                    game.getPlayer().animalMap.get(animal).generateResources(game);
                    seedField.animalRend = false;
                }
            } else if (mouseEvent.getButton() == MouseButton.SECONDARY && seedField.isUnlock && seedField.isReady()) {
                Rectangle overlay = new Rectangle();
                HBox buttons = new HBox();
                overlay.setId("Overlay");
                overlay.setFill(Color.rgb(0, 0, 0, 0.2));
                overlay.widthProperty().bind(game.getGameBoardStack().widthProperty());
                overlay.heightProperty().bind(game.getGameBoardStack().heightProperty());
                overlay.setOnMouseClicked(overlayE -> {
                    game.getGameBoardStack().getChildren().remove(overlay);
                    game.getGameBoardStack().getChildren().remove(buttons);
                });
                buttons.setSpacing(200);
                double width = 200;

                String[] states = {"SeedField", "Enclosure", "Building"};
                for (String state : states) {
                    if (!Objects.equals(seedField.state, state)) {
                        StackPane stackPane = new StackPane();
                        Image choice = SpriteLib.spriteImg("BLANK", 4);
                        ImageView choiceView = new ImageView(choice);
                        HBox textBox = new HBox();
                        String[] text = state.split("");
                        double tempHeight = 0;
                        double tempWidth = 0;
                        width = choice.getWidth() + width;
                        for (String split : text) {
                            Image imageText = SpriteLib.spriteImgLetters(split.toUpperCase(), 2);
                            ImageView imageTextView = new ImageView(imageText);
                            imageTextView.setSmooth(false);
                            tempHeight = imageText.getHeight();
                            tempWidth = imageText.getWidth();

                            textBox.getChildren().add(imageTextView);
                        }

                        stackPane.setOnMouseEntered(e -> {
                            choiceView.setImage(SpriteLib.spriteImg("ONBLANK", 4));
                        });
                        stackPane.setOnMousePressed(e -> {
                            choiceView.setImage(SpriteLib.spriteImg("CLICKBLANK", 4));
                        });
                        stackPane.setOnMouseReleased(e -> {
                            choiceView.setImage(SpriteLib.spriteImg("BLANK", 4));
                            game.getGameBoardStack().getChildren().remove(overlay);
                            game.getGameBoardStack().getChildren().remove(buttons);
                            int money = game.getPlayer().getMoney();
                            switch (state) {
                                case "SeedField", "Enclosure":
                                    if (money >= seedField.getPrice()) {
                                        seedField.state = state;
                                        seedField.fieldColor(top);
                                        game.getPlayer().setMoney(money - seedField.getPrice());
                                        Money.createMoney(game);
                                    }
                                    break;
                                case "Building":
                                    if (money >= seedField.buildingPrice) {
                                        seedField.state = state;
                                        seedField.fieldColor(top);
                                        game.getPlayer().setMoney(money - seedField.buildingPrice);
                                        Money.createMoney(game);
                                    }
                                    break;
                            }
                        });
                        stackPane.setOnMouseExited(e -> {
                            choiceView.setImage(SpriteLib.spriteImg("BLANK", 4));
                        });

                        stackPane.getChildren().addAll(choiceView, textBox);
                        stackPane.setMaxHeight(tempHeight);
                        stackPane.setTranslateY((overlay.getHeight() - tempHeight) / 2);
                        //stackPane.setStyle("-fx-border-color: red; -fx-border-width: 2;");
                        buttons.getChildren().add(stackPane);
                        textBox.setSpacing(5);
                        textBox.setMaxSize(tempWidth, tempHeight);
                    }
                }
                game.getGameBoardStack().getChildren().addAll(overlay, buttons);
                buttons.setMaxWidth(width);
            } else if (!Objects.equals(seedField.getActualAnimal(), "None")){
                if (game.seedFields[fieldId].animalRend) {
                    String animal = seedField.getActualAnimal();
                    game.getPlayer().animalMap.get(animal).generateResources(game);
                    seedField.animalRend = false;
                }
                game.seedFields[fieldId].setActualAnimal("None");
            }
        });

        top.setOnDragOver(e -> {
            Dragboard db = e.getDragboard();
            String name = db.getString();
            String[] nameSplit = name.split("_");
            if (seedField.isUnlock) {
                if (seedField.isReady() && Objects.equals(seedField.state, "SeedField") && Objects.equals(nameSplit[1], "Seeds")) {
                    top.setFill(Color.RED);
                    e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                } else if (Objects.equals(seedField.state, "Enclosure") && (Objects.equals(nameSplit[1], "Animals")) && Objects.equals(seedField.getActualAnimal(), "None")) {
                    if (game.getPlayer().animalMap.get(nameSplit[0]).inEnclos < game.getPlayer().animalMap.get(nameSplit[0]).getQuantity()){
                        top.setFill(Color.RED);
                        e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }
                } else if (Objects.equals(seedField.state, "Enclosure") && (Objects.equals(nameSplit[0], seedField.getActualAnimal())) && game.getPlayer().foodMap.get(nameSplit[0]).getQuantity() > 0) {
                    top.setFill(Color.RED);
                    e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
            }
            e.consume();
        });

        top.setOnDragExited(e -> {
            if (seedField.isUnlock) {
                if (seedField.isReady() && Objects.equals(seedField.state, "SeedField")) {
                    top.setFill(Color.GREEN);
                } else if (Objects.equals(seedField.state, "Enclosure")) {
                    if (seedField.isReady()) top.setFill(Color.KHAKI);
                    else top.setFill(Color.BROWN);
                }
            }
        });

        top.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            String name = db.getString();
            String[] nameSplit = name.split("_");
            if (seedField.isUnlock) {
                if (Objects.equals(seedField.state, "SeedField") && Objects.equals(nameSplit[1], "Seeds")) {
                    if (game.getPlayer().seedMap.get(nameSplit[0]).getQuantity() > 0) {
                        top.setFill(Color.YELLOW);
                        seedField.setActualSeed(nameSplit[0]);
                        SeedController.plantSeed(game, fieldId, nameSplit[0]);
                    }
                } else if (Objects.equals(seedField.state, "Enclosure")) {
                    if (Objects.equals(nameSplit[1], "Animals")) {
                        top.setFill(Color.BROWN);
                        seedField.setActualAnimal(nameSplit[0]);
                        if (seedField.isReady()) {
                            game.getPlayer().animalMap.get(nameSplit[0]).inEnclos++;
                        }
                        seedField.setReady(false);
                    } else if (Objects.equals(nameSplit[1], "Food") && Objects.equals(nameSplit[0], seedField.getActualAnimal())){
                        feed(game, fieldId, nameSplit[0]);
                    }
                }
            }
            //seedField.isUnlock && seedField.isReady()
            //                    && Objects.equals(seedField.state, "SeedField")

            //Objects.equals(seedField.state, "Enclosure")
        });

        top.setOnMouseEntered(mouseEvent -> {
            if (!seedField.isUnlock) {
                {
                    top.setFill(Color.DARKRED);
                    right.setFill(Color.DARKRED);
                    left.setFill(Color.DARKRED);
                }
            } else if (seedField.isReady()) {
                seedField.fieldOverColor(top);
            }
        });

        top.setOnMouseExited(mouseEvent -> {
            if (!seedField.isUnlock) {
                top.setFill(Color.LIGHTBLUE);
                right.setFill(Color.LIGHTBLUE);
                left.setFill(Color.LIGHTBLUE);
            } else if (seedField.isReady()){
                seedField.fieldColor(top);
            }
        });
    }
}
