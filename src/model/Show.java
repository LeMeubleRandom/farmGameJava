package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Show {
    private String[] dragNDrop = {"Animals", "Food", "Seeds"};
    private String[] seed = {"Carot", "Corn", "Peas", "Potatoes", "Tomatoes", "Radish", "Beet", "Lettuce", "Broccoli"};
    private String[] resource = {"Carot", "Corn", "Peas", "Potatoes", "Tomatoes", "Radish", "Beet", "Lettuce", "Broccoli", "Pig", "Cow", "Hen", "Sheep"};
    private String[] animal = {"Cow", "Hen", "Pig", "Sheep"};
    public String number = "0";
    public String[] tabs = {"Seeds", "Resources", "Animals"};
    public String[] tabDrag = {"Animal", "Food", "Seed"};
    private Game game;
    public static boolean inventoryState = false;
    public static boolean shopState = false;
    public ScrollPane[] myScrolls= new ScrollPane[3];

    public Show(Game game) {
        this.game = game;
    }

    public void buildInterface() {
        Image save = SpriteLib.spriteImg("ONSAVE", 3);
        ImageView saveView = new ImageView(save);
        game.getGameBoard().getChildren().add(saveView);
        saveView.setTranslateX(1280 - save.getWidth() - 10);
        saveView.setTranslateY(10);
        saveView.setStyle("-fx-border-color: red; -fx-border-width: 2;");

        saveView.setOnMousePressed(e -> {
            saveView.setImage(SpriteLib.spriteImg("CLICKSAVE", 3));
        });

        saveView.setOnMouseReleased(e -> {
            saveView.setImage(SpriteLib.spriteImg("ONSAVE", 3));
        });

        createDragTab(game);
        StackPane count = new StackPane();
        Money.createMoney(game);
        Image bg = SpriteLib.spriteImg("ONBLANK", 3);
        ImageView imgv = new ImageView(bg);
        int countWidth = 200;
        int countHeight = 45;
        imgv.setFitWidth(countWidth);
        imgv.setFitHeight(countHeight);
        HBox textBox = new HBox();
        textBox.setId("text_box");
        textBox.setPrefSize(countWidth, countHeight);
        textBox.setAlignment(Pos.CENTER);
        //textBox.setStyle("-fx-border-color: red; -fx-border-width: 2;");
        //count.setStyle("-fx-border-color: red; -fx-border-width: 2;");
        //.setStyle("-fx-border-color: red; -fx-border-width: 2;");

        count.getChildren().addAll(imgv, textBox);
        game.getGameBoard().getChildren().add(count);

        count.setId("stack");
        count.setTranslateX(5);
        count.setTranslateY(720 - bg.getHeight());
        Integer[] arraySplit = SpriteLib.splitNumber(number);

        for (Integer s : arraySplit) {
            Image image = SpriteLib.spriteImgNumbers(s, 2);
            ImageView imageView = new ImageView(image);
            imageView.setSmooth(false);
            textBox.setSpacing(5);

            textBox.getChildren().add(imageView);
        }
        for (int i = 0; i < dragNDrop.length; i++) {
            game.getGameBoard().getChildren().remove(game.getGameBoard().lookup("#" + dragNDrop[i] + "tab"));
        }
        for (int j = 0; j < dragNDrop.length; j++) {
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setId(dragNDrop[j] + "tab");
            scrollPane.setVisible(false);
            myScrolls[j] = scrollPane;
            scrollPane.setStyle("-fx-background-radius: 15; -fx-background-color: #000000;");
            VBox container = new VBox();
            /*
            container.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            scrollPane.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-fill: transparent;");
            container.setStyle("-fx-background-color: transparent;");
            */
            container.setSpacing(5);
            container.setPadding(new Insets(5));
            ColorAdjust grayscale = new ColorAdjust();
            grayscale.setSaturation(-1.0);

            scrollPane.addEventFilter(ScrollEvent.SCROLL, event -> {
                double vitesse = 3;

                double deltaY = event.getDeltaY() * vitesse;
                double width = scrollPane.getContent().getBoundsInLocal().getHeight();
                double vvalue = scrollPane.getVvalue();

                scrollPane.setVvalue(vvalue - deltaY / width);
                event.consume();
            });

            String[] tabChoice = {};

            tabChoice = switch (dragNDrop[j]) {
                case "Seeds" -> seed;
                case "Animals", "Food" -> animal;
                default -> tabChoice;
            };

            for (int i = 0; i < tabChoice.length; i++) {
                Image bgVege = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/vegetables/PNG/bg.png")));
                ImageView imgVege = new ImageView(bgVege);
                Image vege = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/" + dragNDrop[j] + "/N" + i + ".png")));
                StackPane stackPane = new StackPane();
                ImageView vegev = new ImageView(vege);
                vegev.setId(tabChoice[i]);

                boolean unlockState = true;
                switch (dragNDrop[j]) {
                    case "Seeds":
                        if (!game.getPlayer().seedMap.get(tabChoice[i]).isUnlock) {
                            vegev.setEffect(grayscale);
                            unlockState = false;
                        }
                        break;
                    case "Animals":
                        if (!game.getPlayer().animalMap.get(tabChoice[i]).isUnlock()) {
                            vegev.setEffect(grayscale);
                            unlockState = false;
                        }
                        break;
                    case "Food":
                        if (!game.getPlayer().foodMap.get(tabChoice[i]).isUnlock()) {
                            vegev.setEffect(grayscale);
                            unlockState = false;
                        }
                        break;
                }

                int size = 70;
                imgVege.setFitWidth(size);
                imgVege.setFitHeight(size);
                vegev.setFitWidth(size);
                vegev.setFitHeight(size);

                stackPane.getChildren().addAll(imgVege, vegev);

                int finalI = i;

                boolean finalUnlockState = unlockState;
                int finalJ = j;
                String[] finalTabChoice = tabChoice;
                stackPane.setOnMouseEntered(e -> {
                    if (finalUnlockState) {
                        Glow glow = new Glow(0.8);
                        vegev.setEffect(glow);

                        int quantity = getQuantity(dragNDrop[finalJ], finalTabChoice[finalI]);
                        textBox.getChildren().clear();

                        String quantityString = String.valueOf(quantity);
                        Integer[] quantityArray = SpriteLib.splitNumber(quantityString);

                        for (Integer index : quantityArray) {
                            Image digit = SpriteLib.spriteImgNumbers(index, 2);
                            ImageView digitView = new ImageView(digit);
                            digitView.setSmooth(false);

                            textBox.getChildren().add(digitView);
                        }
                    }
                });

                stackPane.setOnMouseExited(e -> {
                    if (finalUnlockState) vegev.setEffect(null);
                });

                stackPane.setOnDragDone(e -> {
                    actualSwap("None", dragNDrop[finalJ]);
                });

                stackPane.setOnDragDetected(e -> {
                    if (finalUnlockState) {
                        Dragboard db = vegev.startDragAndDrop(TransferMode.MOVE);
                        SnapshotParameters params = new SnapshotParameters();
                        params.setFill(Color.TRANSPARENT);

                        db.setDragView(vegev.snapshot(params, null));
                        db.setDragViewOffsetX(35);
                        db.setDragViewOffsetY(35);
                        ClipboardContent content = new ClipboardContent();
                        content.putString(finalTabChoice[finalI] + "_" + dragNDrop[finalJ]);
                        db.setContent(content);

                        actualSwap(finalTabChoice[finalI], dragNDrop[finalJ]);

                        e.consume();
                    }
                });
                container.getChildren().add(stackPane);
            }

            //container.setStyle("-fx-background-color: #2D2D2D; -fx-background-insets: 0;");
            //scrollPane.setStyle("-fx-background-color: #2D2D2D; -fx-background-insets: 0;");
            scrollPane.setContent(container);
            scrollPane.setFitToWidth(true);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            scrollPane.layoutYProperty().set(100);
            scrollPane.prefHeightProperty().bind(game.getGameBoard().heightProperty().subtract(150));
            scrollPane.prefWidthProperty().set(85);
            scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-fill: transparent;");

            game.getGameBoard().getChildren().addAll(scrollPane);
        }
    }

    public void buildShop() {
        String btnType = "SHOP";
        Image image = SpriteLib.spriteImg(btnType, 3);
        ImageView imageView = new ImageView(image);
        imageView.setSmooth(false);
        imageView.setLayoutX(1280 - 173);
        imageView.setLayoutY(720 - 41);

        imageView.setOnMousePressed(e -> {
            imageView.setImage(SpriteLib.spriteImg("CLICK" + btnType, 3));
        });

        imageView.setOnMouseReleased(e -> {
            if (!shopState) {
                game.getGameBoard().lookup("#text_box").setVisible(false);
                imageView.setImage(SpriteLib.spriteImg(btnType, 3));
                shopState = true;
                StackPane stackPane = new StackPane();
                stackPane.setId("Shop");
                Pane pane = new Pane();

                Image title = SpriteLib.spriteImg("ON" + btnType, 4);
                ImageView titleView = new ImageView(title);
                pane.getChildren().add(titleView);

                imageView.setImage(SpriteLib.spriteImg(btnType, 3));
                Image shop = SpriteLib.spriteImgInventory("EMPTY", 7);
                ImageView shopView = new ImageView(shop);

                Rectangle overlay = new Rectangle();
                overlay.setId("Overlay");
                overlay.setFill(Color.rgb(0, 0, 0, 0.6));
                overlay.widthProperty().bind(game.getGameBoard().widthProperty());
                overlay.heightProperty().bind(game.getGameBoard().heightProperty());

                overlay.setOnMouseClicked(overlayE -> {
                    shopState = false;
                    Node shopNode = game.getGameBoard().lookup("#Shop");
                    Node overlayNode = game.getGameBoard().lookup("#Overlay");
                    game.getGameBoard().getChildren().remove(shopNode);
                    game.getGameBoard().getChildren().remove(overlayNode);
                    game.getGameBoard().lookup("#text_box").setVisible(true);
                });

                stackPane.getChildren().add(shopView);
                stackPane.getChildren().add(pane);
                setTabs(stackPane);
                pane.toFront();
                pane.setMaxWidth(stackPane.getMaxWidth());
                pane.setMaxHeight(stackPane.getMaxHeight());

                stackPane.setLayoutX((1280 - shop.getWidth()) / 2);
                stackPane.setLayoutY((720 - shop.getHeight()) / 2);
                titleView.setLayoutX((shop.getWidth() - title.getWidth()) / 2);
                titleView.setLayoutY(22);

                game.getGameBoard().getChildren().add(overlay);
                game.getGameBoard().getChildren().add(stackPane);
                imageView.setPickOnBounds(true);
                pane.setMouseTransparent(true);

                Money.createMoney(game);
            }
        });

        imageView.setOnMouseEntered(e -> {
            imageView.setImage(SpriteLib.spriteImg("ON" + btnType, 3));
        });

        imageView.setOnMouseExited(e -> {
            imageView.setImage(SpriteLib.spriteImg(btnType, 3));
        });

        game.getGameBoard().getChildren().add(imageView);
        imageView.toFront();
        imageView.setPickOnBounds(true);
    }

    public void buildInventory() {
        String btnType = "INVENTORY";
        Image image = SpriteLib.spriteImg(btnType, 3);
        ImageView imageView = new ImageView(image);
        imageView.setSmooth(false);
        imageView.setLayoutX(1280 - 173);
        imageView.setLayoutY(720 - 82);

        imageView.setOnMousePressed(e -> {
            imageView.setImage(SpriteLib.spriteImg("CLICK" + btnType, 3));
        });

        imageView.setOnMouseReleased(e -> {
            if (!inventoryState) {
                game.getGameBoard().lookup("#text_box").setVisible(false);
                Money.createMoney(game);
                inventoryState = true;
                StackPane stackPane = new StackPane();
                stackPane.setId("Inventory");
                Pane pane = new Pane();

                Image title = SpriteLib.spriteImg("ON" + btnType, 4);
                ImageView titleView = new ImageView(title);
                pane.getChildren().add(titleView);

                imageView.setImage(SpriteLib.spriteImg(btnType, 3));
                Image inventory = SpriteLib.spriteImgInventory("EMPTY", 7);
                ImageView inventoryView = new ImageView(inventory);

                Rectangle overlay = new Rectangle();
                overlay.setId("Overlay");
                overlay.setFill(Color.rgb(0, 0, 0, 0.6));
                overlay.widthProperty().bind(game.getGameBoard().widthProperty());
                overlay.heightProperty().bind(game.getGameBoard().heightProperty());

                overlay.setOnMouseClicked(overlayE -> {
                    inventoryState = false;
                    Node inventoryNode = game.getGameBoard().lookup("#Inventory");
                    Node overlayNode = game.getGameBoard().lookup("#Overlay");
                    game.getGameBoard().getChildren().remove(inventoryNode);
                    game.getGameBoard().getChildren().remove(overlayNode);
                    game.getGameBoard().lookup("#text_box").setVisible(true);
                });

                stackPane.getChildren().add(inventoryView);
                stackPane.getChildren().add(pane);
                setTabs(stackPane);
                pane.toFront();
                pane.setMaxWidth(stackPane.getMaxWidth());
                pane.setMaxHeight(stackPane.getMaxHeight());

                stackPane.setLayoutX((1280 - inventory.getWidth()) / 2);
                stackPane.setLayoutY((720 - inventory.getHeight()) / 2);
                titleView.setLayoutX((inventory.getWidth() - title.getWidth()) / 2);
                titleView.setLayoutY(22);

                game.getGameBoard().getChildren().add(overlay);
                game.getGameBoard().getChildren().add(stackPane);
                imageView.setPickOnBounds(true);
                pane.setMouseTransparent(true);

                Money.createMoney(game);
            }
        });

        imageView.setOnMouseEntered(e -> {
            imageView.setImage(SpriteLib.spriteImg("ON" + btnType, 3));
        });

        imageView.setOnMouseExited(e -> {
            imageView.setImage(SpriteLib.spriteImg(btnType, 3));
        });

        game.getGameBoard().getChildren().add(imageView);
        imageView.toFront();
        imageView.setPickOnBounds(true);
    }

    public void setTabs(StackPane stackPane) {
        HBox container = new HBox();
        container.setSpacing(15);
        container.setPadding(new Insets(5));
        ColorAdjust grayscale = new ColorAdjust();
        grayscale.setSaturation(-1.0);
        ImageView[] imgs = new ImageView[tabs.length];

        for (int i = 0; i < tabs.length; i++) {
            Image bg = SpriteLib.spriteImg("BLANK", 4);
            ImageView bgView = new ImageView(bg);
            imgs[i] = bgView;
            bgView.setId("bouton" + i);
            HBox textBox = new HBox();
            int countWidth = 190;
            int countHeight = 45;
            bgView.setFitWidth(countWidth);
            bgView.setFitHeight(countHeight);
            container.setPrefSize(countWidth, countHeight);
            container.setAlignment(Pos.CENTER);
            String[] letters = tabs[i].split("");
            double letterWidth = 0;
            double letterHeight = 0;

            for (String letter : letters) {
                Image image = SpriteLib.spriteImgLetters(letter.toUpperCase(), 2);
                ImageView imageView = new ImageView(image);
                imageView.setSmooth(false);
                textBox.setSpacing(7);
                letterWidth = letterWidth + image.getWidth();
                letterHeight = image.getHeight();

                textBox.getChildren().add(imageView);
            }
            StackPane tab = new StackPane();
            textBox.setMaxSize(letterWidth, letterHeight);

            tab.getChildren().addAll(bgView, textBox);
            tab.setMaxWidth(letterWidth);
            tab.setMaxHeight(letterHeight);

            container.setTranslateY(-210);
            container.setMaxHeight(bg.getHeight());
            container.getChildren().add(tab);
            bgView.setMouseTransparent(false);
            textBox.setMouseTransparent(true);
            tab.setPickOnBounds(true);

            int finalI = i;
            tab.setOnMousePressed(e -> {
                Node oldSharpView = game.getGameBoard().lookup("#sharpView");
                if (oldSharpView != null) ((Pane) oldSharpView.getParent()).getChildren().remove(oldSharpView);
                for (int j = 0; j < tabs.length; j++) {
                    imgs[j].setImage(SpriteLib.spriteImg("BLANK", 4));
                }
                Image cracks = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/ui/PNG/Decorative_cracks.png")));
                PixelReader reader = cracks.getPixelReader();
                WritableImage cropped = new WritableImage(
                        reader,
                        320,
                        0,
                        32,
                        20
                );
                Image sharp = SpriteLib.getUpscaledImage(cropped, 4);
                ImageView sharpView = new ImageView(sharp);
                sharpView.setId("sharpView");
                tab.getChildren().add(sharpView);
                sharpView.setTranslateY(62);
                bgView.setImage(SpriteLib.spriteImg("CLICKBLANK", 4));

                Image image = createFillCase(stackPane);
                if (Objects.equals(stackPane.getId(), "Inventory")) {
                    createInventory(stackPane, tabs[finalI], image);
                } else if (Objects.equals(stackPane.getId(), "Shop")) {
                    createShop(stackPane, tabs[finalI], image);
                }
            });
        }
        container.toFront();
        stackPane.getChildren().add(container);
    }

    public void createInventory(StackPane parentPane, String menuName, Image image) {
        parentPane.getChildren().remove(parentPane.lookup("#inventory"));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setId("inventory");
        VBox container = new VBox();
        String[] menu = new String[0];
        ColorAdjust grayscale = new ColorAdjust();
        grayscale.setSaturation(-1.0);

        menu = switch (menuName) {
            case "Seeds" -> seed;
            case "Resources" -> resource;
            case "Animals" -> animal;
            default -> menu;
        };

        String[] food = {"Cow", "Hen", "Pig", "Sheep"};
        if (menuName.equals("Animals"))
            for (int i = 0; i < food.length; i++) {
                StackPane stackPane = new StackPane();
                Pane pane = new Pane();
                Image bg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/vegetables/PNG/bg.png")));
                ImageView bgView = new ImageView(bg);
                Image element = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/Food/N" + i + ".png")));
                ImageView elementView = new ImageView(element);

                if (!game.getPlayer().animalMap.get(food[i]).isUnlock()) elementView.setEffect(grayscale);

                elementView.setId(menuName + "_" + i);
                int size = 80;

                bgView.setFitWidth(size);
                bgView.setFitHeight(size);
                elementView.setFitWidth(size);
                elementView.setFitHeight(size);

                HBox hBox = createResourcesNumber(i, food, elementView, "Food");
                hBox.setId("ressources_number");

                StackPane sellButton = new StackPane();
                Image sell = SpriteLib.spriteImg("BLANK", 3);
                ImageView sellView = new ImageView(sell);
                HBox sellTextBox = new HBox();
                String sellText = "SELL";
                String[] sellTextSplit = sellText.split("");
                double tempHeight = 0;
                double tempWidth = 0;
                for (String split : sellTextSplit) {
                    Image imageText = SpriteLib.spriteImgLetters(split, 2);
                    ImageView imageTextView = new ImageView(imageText);
                    imageTextView.setSmooth(false);
                    tempHeight = imageText.getHeight();
                    tempWidth = imageText.getWidth();

                    sellTextBox.getChildren().add(imageTextView);
                }
                sellButton.getChildren().addAll(sellView, sellTextBox);
                sellButton.setLayoutY((bg.getHeight() - sell.getHeight()) / 2);
                sellTextBox.setSpacing(5);
                sellTextBox.setMaxSize(tempWidth, tempHeight);

                HBox priceNumber = showPrice("cost", "Food", food, i, bg.getHeight());

                String[] finalMenu = food;
                int finalI = i;
                final HBox[] currentHBox = {hBox};
                sellButton.setOnMousePressed(e -> {
                    int quantity = game.getPlayer().getMapQuantity("Food", finalMenu[finalI]);
                    if (quantity > 0) {
                        int amount = game.getPlayer().getMoney();
                        game.getPlayer().setMoney(amount + game.getPlayer().getMapPrice("Food", finalMenu[finalI]));
                        game.getPlayer().setMapQuantity("Food", finalMenu[finalI],quantity, -1 );
                        HBox newHBox = createResourcesNumber(finalI, finalMenu, elementView, "Food");
                        int index = pane.getChildren().indexOf(currentHBox[0]);
                        if (index != -1) {
                            pane.getChildren().set(index, newHBox);
                        }
                        Money.createMoney(game);
                        currentHBox[0] = newHBox;
                        sellView.setImage(SpriteLib.spriteImg("CLICKBLANK", 3));
                    }
                });

                sellButton.setOnMouseReleased(e -> {
                    sellView.setImage(SpriteLib.spriteImg("BLANK", 3));
                });

                stackPane.getChildren().addAll(bgView, elementView);
                pane.getChildren().addAll(stackPane, hBox, sellButton, priceNumber);
                container.getChildren().add(pane);
            }
        for (int i = 0; i < menu.length; i++) {
            StackPane stackPane = new StackPane();
            Pane pane = new Pane();
            Image bg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/vegetables/PNG/bg.png")));
            ImageView bgView = new ImageView(bg);
            Image element = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/" + menuName + "/N" + i + ".png")));
            ImageView elementView = new ImageView(element);

            switch (menuName) {
                case "Seeds":
                    if (!game.getPlayer().seedMap.get(menu[i]).isUnlock) elementView.setEffect(grayscale);
                    break;
                case "Resources":
                    if (!game.getPlayer().resourceMap.get(menu[i]).isUnlock()) elementView.setEffect(grayscale);
                    break;
                case "Animals":
                    if (!game.getPlayer().animalMap.get(menu[i]).isUnlock()) elementView.setEffect(grayscale);
                    break;
            }

            elementView.setId(menuName + "_" + i);
            int size = 80;

            bgView.setFitWidth(size);
            bgView.setFitHeight(size);
            elementView.setFitWidth(size);
            elementView.setFitHeight(size);

            HBox hBox = createResourcesNumber(i, menu, elementView, menuName);
            hBox.setId("ressources_number");

            StackPane sellButton = new StackPane();
            Image sell = SpriteLib.spriteImg("BLANK", 3);
            ImageView sellView = new ImageView(sell);
            HBox sellTextBox = new HBox();
            String sellText = "SELL";
            String[] sellTextSplit = sellText.split("");
            double tempHeight = 0;
            double tempWidth = 0;
            for (String split : sellTextSplit) {
                Image imageText = SpriteLib.spriteImgLetters(split, 2);
                ImageView imageTextView = new ImageView(imageText);
                imageTextView.setSmooth(false);
                tempHeight = imageText.getHeight();
                tempWidth = imageText.getWidth();

                sellTextBox.getChildren().add(imageTextView);
            }
            sellButton.getChildren().addAll(sellView, sellTextBox);
            sellButton.setLayoutY((bg.getHeight() - sell.getHeight()) / 2);
            sellTextBox.setSpacing(5);
            sellTextBox.setMaxSize(tempWidth, tempHeight);

            HBox priceNumber = showPrice("SellPrice", menuName, menu, i, bg.getHeight());

            String[] finalMenu = menu;
            int finalI = i;
            final HBox[] currentHBox = {hBox};
            sellButton.setOnMousePressed(e -> {
                int quantity = game.getPlayer().getMapQuantity(menuName, finalMenu[finalI]);
                int min = (menuName.equals("Animals")) ? game.getPlayer().animalMap.get(finalMenu[finalI]).inEnclos : 0;
                if (quantity > min) {
                    int amount = game.getPlayer().getMoney();
                    game.getPlayer().setMoney(amount + game.getPlayer().getMapPrice(menuName, finalMenu[finalI]));
                    game.getPlayer().setMapQuantity(menuName, finalMenu[finalI],quantity, -1 );
                    HBox newHBox = createResourcesNumber(finalI, finalMenu, elementView, menuName);
                    int index = pane.getChildren().indexOf(currentHBox[0]);
                    if (index != -1) {
                        pane.getChildren().set(index, newHBox);
                    }
                    Money.createMoney(game);
                    currentHBox[0] = newHBox;
                    sellView.setImage(SpriteLib.spriteImg("CLICKBLANK", 3));
                }
            });

            sellButton.setOnMouseReleased(e -> {
                sellView.setImage(SpriteLib.spriteImg("BLANK", 3));
            });

            stackPane.getChildren().addAll(bgView, elementView);
            pane.getChildren().addAll(stackPane, hBox, sellButton, priceNumber);
            container.getChildren().add(pane);
            //sellButton.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            //.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            //hBox.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            //sellTextBox.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            //pane.setStyle("-fx-border-color: red; -fx-border-width: 2;");
        }
        scrollPane.setContent(container);
        scrollPane.setMaxSize(image.getWidth() - 85, image.getHeight() - 90);
        container.setSpacing(30);
        container.setStyle("-fx-background-color: transparent;");
        scrollPane.setStyle(
                "-fx-background-color: transparent; " + "-fx-background: transparent;"
        );
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        StackPane.setMargin(scrollPane, new Insets(140, 0, 0, 15));
        parentPane.getChildren().add(scrollPane);

        scrollPane.addEventFilter(ScrollEvent.SCROLL, event -> {
            double vitesse = 4;

            double deltaY = event.getDeltaY() * vitesse;
            double width = scrollPane.getContent().getBoundsInLocal().getHeight();
            double vvalue = scrollPane.getVvalue();

            scrollPane.setVvalue(vvalue - deltaY / width);
            event.consume();
        });
    }

    public void createShop(StackPane parentPane, String menuName, Image image) {
        parentPane.getChildren().remove(parentPane.lookup("#shop"));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setId("shop");
        VBox container = new VBox();
        String[] menu = new String[0];
        ColorAdjust grayscale = new ColorAdjust();
        grayscale.setSaturation(-1.0);

        menu = switch (menuName) {
            case "Seeds" -> seed;
            case "Resources" -> resource;
            case "Animals" -> animal;
            default -> menu;
        };

        String[] food = {"Cow", "Hen", "Pig", "Sheep"};
        if (menuName.equals("Animals"))
            for (int i = 0; i < food.length; i++) {
                StackPane stackPane = new StackPane();
                Pane pane = new Pane();
                Image bg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/vegetables/PNG/bg.png")));
                ImageView bgView = new ImageView(bg);
                Image element = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/Food/N" + i + ".png")));
                ImageView elementView = new ImageView(element);

                if (!game.getPlayer().animalMap.get(food[i]).isUnlock()) elementView.setEffect(grayscale);

                elementView.setId(menuName + "_" + i);
                int size = 80;

                bgView.setFitWidth(size);
                bgView.setFitHeight(size);
                elementView.setFitWidth(size);
                elementView.setFitHeight(size);

                HBox hBox = createResourcesNumber(i, food, elementView, "Food");
                hBox.setId("ressources_number");

                StackPane sellButton = new StackPane();
                Image sell = SpriteLib.spriteImg("BLANK", 3);
                ImageView sellView = new ImageView(sell);
                HBox sellTextBox = new HBox();
                String sellText = "BUY";
                String[] sellTextSplit = sellText.split("");
                double tempHeight = 0;
                double tempWidth = 0;
                for (String split : sellTextSplit) {
                    Image imageText = SpriteLib.spriteImgLetters(split, 2);
                    ImageView imageTextView = new ImageView(imageText);
                    imageTextView.setSmooth(false);
                    tempHeight = imageText.getHeight();
                    tempWidth = imageText.getWidth();

                    sellTextBox.getChildren().add(imageTextView);
                }
                sellButton.getChildren().addAll(sellView, sellTextBox);
                sellButton.setLayoutY((bg.getHeight() - sell.getHeight()) / 2);
                sellTextBox.setSpacing(5);
                sellTextBox.setMaxSize(tempWidth, tempHeight);

                HBox priceNumber = showPrice("cost", "Food", food, i, bg.getHeight());

                String[] finalMenu = food;
                int finalI = i;
                final HBox[] currentHBox = {hBox};
                sellButton.setOnMousePressed(e -> {
                    int quantity = game.getPlayer().getMapQuantity("Food", finalMenu[finalI]);
                    if (quantity > 0) {
                        int amount = game.getPlayer().getMoney();
                        game.getPlayer().setMoney(amount - game.getPlayer().getMapPrice("Food", finalMenu[finalI]));
                        game.getPlayer().setMapQuantity("Food", finalMenu[finalI],quantity, +1 );
                        HBox newHBox = createResourcesNumber(finalI, finalMenu, elementView, "Food");
                        int index = pane.getChildren().indexOf(currentHBox[0]);
                        if (index != -1) {
                            pane.getChildren().set(index, newHBox);
                        }
                        Money.createMoney(game);
                        currentHBox[0] = newHBox;
                        sellView.setImage(SpriteLib.spriteImg("CLICKBLANK", 3));
                    }
                });

                sellButton.setOnMouseReleased(e -> {
                    sellView.setImage(SpriteLib.spriteImg("BLANK", 3));
                });

                stackPane.getChildren().addAll(bgView, elementView);
                pane.getChildren().addAll(stackPane, hBox, sellButton, priceNumber);
                container.getChildren().add(pane);
            }

        for (int i = 0; i < menu.length; i++) {
            StackPane stackPane = new StackPane();
            Pane pane = new Pane();
            Image bg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/vegetables/PNG/bg.png")));
            ImageView bgView = new ImageView(bg);
            Image element = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/" + menuName + "/N" + i + ".png")));
            ImageView elementView = new ImageView(element);

            switch (menuName) {
                case "Seeds":
                    if (!game.getPlayer().seedMap.get(menu[i]).isUnlock) elementView.setEffect(grayscale);
                    break;
                case "Resources":
                    if (!game.getPlayer().resourceMap.get(menu[i]).isUnlock()) elementView.setEffect(grayscale);
                    break;
                case "Animals":
                    if (!game.getPlayer().animalMap.get(menu[i]).isUnlock()) elementView.setEffect(grayscale);
                    break;
            }

            elementView.setId(menuName + "_" + i);
            int size = 80;

            bgView.setFitWidth(size);
            bgView.setFitHeight(size);
            elementView.setFitWidth(size);
            elementView.setFitHeight(size);

            HBox hBox = createResourcesNumber(i, menu, elementView, menuName);
            hBox.setId("ressources_number");

            StackPane sellButton = new StackPane();
            Image sell = SpriteLib.spriteImg("BLANK", 3);
            ImageView sellView = new ImageView(sell);
            HBox sellTextBox = new HBox();
            String sellText = "BUY";
            String[] sellTextSplit = sellText.split("");
            double tempHeight = 0;
            double tempWidth = 0;
            for (String split : sellTextSplit) {
                Image imageText = SpriteLib.spriteImgLetters(split, 2);
                ImageView imageTextView = new ImageView(imageText);
                imageTextView.setSmooth(false);
                tempHeight = imageText.getHeight();
                tempWidth = imageText.getWidth();

                sellTextBox.getChildren().add(imageTextView);
            }
            sellButton.getChildren().addAll(sellView, sellTextBox);
            sellButton.setLayoutY((bg.getHeight() - sell.getHeight()) / 2);
            sellTextBox.setSpacing(5);
            sellTextBox.setMaxSize(tempWidth, tempHeight);

            HBox priceNumber = showPrice("cost", menuName, menu, i, bg.getHeight());

            String[] finalMenu = menu;
            int finalI = i;
            final HBox[] currentHBox = {hBox};
            sellButton.setOnMousePressed(e -> {
                int price = game.getPlayer().getMapPrice(menuName, finalMenu[finalI]);
                int money = game.getPlayer().getMoney();
                if (money >= price){
                    game.getPlayer().setMoney(money - price);
                    int quantity = game.getPlayer().getMapQuantity(menuName, finalMenu[finalI]);
                    game.getPlayer().setMapQuantity(menuName, finalMenu[finalI], quantity, +1);
                    HBox newHBox = createResourcesNumber(finalI, finalMenu, elementView, menuName);
                    int index = pane.getChildren().indexOf(currentHBox[0]);
                    if (index != -1) {
                        pane.getChildren().set(index, newHBox);
                    }
                    Money.createMoney(game);
                    currentHBox[0] = newHBox;
                    sellView.setImage(SpriteLib.spriteImg("CLICKBLANK", 3));
                }
            });

            sellButton.setOnMouseReleased(e -> {
                sellView.setImage(SpriteLib.spriteImg("BLANK", 3));
            });

            stackPane.getChildren().addAll(bgView, elementView);
            pane.getChildren().addAll(stackPane, hBox, sellButton, priceNumber);
            container.getChildren().add(pane);
            //sellButton.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            //priceNumber.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            //hBox.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            //sellTextBox.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            //pane.setStyle("-fx-border-color: red; -fx-border-width: 2;");
        }
        scrollPane.setContent(container);
        scrollPane.setMaxSize(image.getWidth() - 85, image.getHeight() - 90);
        container.setSpacing(30);
        container.setStyle("-fx-background-color: transparent;");
        scrollPane.setStyle(
                "-fx-background-color: transparent; " + "-fx-background: transparent;"
        );
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        StackPane.setMargin(scrollPane, new Insets(140, 0, 0, 15));
        parentPane.getChildren().add(scrollPane);

        scrollPane.addEventFilter(ScrollEvent.SCROLL, event -> {
            double vitesse = 4;

            double deltaY = event.getDeltaY() * vitesse;
            double width = scrollPane.getContent().getBoundsInLocal().getHeight();
            double vvalue = scrollPane.getVvalue();

            scrollPane.setVvalue(vvalue - deltaY / width);
            event.consume();
        });
    }

    public Image createFillCase(StackPane stackPane) {
        Image clientCase = SpriteLib.spriteImgTiles("FILLBIGCASE", 17);
        ImageView imageView = new ImageView(clientCase);
        stackPane.getChildren().add(imageView);
        imageView.setFitWidth(clientCase.getWidth() + 15);
        imageView.setFitHeight(clientCase.getHeight() + 20);
        imageView.setTranslateY(75);

        return clientCase;
    }

    public HBox createResourcesNumber(int index, String[] menu, ImageView elementView, String menuName) {
        String numberString = "number";
        HBox hBox = new HBox();
        hBox.setSpacing(7);
        String[] numberArray = numberString.split("");
        double tempHeight = 0;
        for (String number : numberArray) {
            Image numberImage = SpriteLib.spriteImgLetters(number.toUpperCase(), 2);
            ImageView numberView = new ImageView(numberImage);
            numberView.setSmooth(false);
            tempHeight = numberImage.getHeight();

            hBox.getChildren().add(numberView);
        }

        String menuNumbers = String.valueOf(game.getPlayer().getMapQuantity(menuName, menu[index]));
        Integer[] intArray = SpriteLib.splitNumber(menuNumbers);

        String[] splitters = {"ESPACE", "TWOPOINTS", "ESPACE"};
        for (String splitter : splitters) {
            Image splitImage = SpriteLib.spriteImgLetters(splitter, 2);
            ImageView splitView = new ImageView(splitImage);
            splitView.setSmooth(false);

            hBox.getChildren().add(splitView);
        }

        for (Integer menuNumber : intArray) {
            Image digit = SpriteLib.spriteImgNumbers(menuNumber, 2);
            ImageView digitView = new ImageView(digit);
            digitView.setSmooth(false);

            hBox.getChildren().add(digitView);
        }
        hBox.setTranslateX(120);
        hBox.setTranslateY((elementView.getFitHeight() - tempHeight) / 2);

        return hBox;
    }

    public HBox showPrice(String message, String menuName, String[] menu, int index, double parentHeight) {
        HBox priceNumber = new HBox();
        priceNumber.setSpacing(7);
        String[] costArray = message.split("");
        String[] splitters = {"ESPACE", "TWOPOINTS", "ESPACE"};
        String price = String.valueOf(game.getPlayer().getMapPrice(menuName, menu[index]));
        Integer[] intArray = SpriteLib.splitNumber(price);

        double tempHeight = 0;

        for (String costLetter : costArray) {
            Image costImage = SpriteLib.spriteImgLetters(costLetter.toUpperCase(), 2);
            ImageView costView = new ImageView(costImage);
            costView.setSmooth(false);
            tempHeight = costImage.getHeight();

            priceNumber.getChildren().add(costView);
        }

        for (String splitter : splitters) {
            Image splitImage = SpriteLib.spriteImgLetters(splitter, 2);
            ImageView splitView = new ImageView(splitImage);
            splitView.setSmooth(false);

            priceNumber.getChildren().add(splitView);
        }

        for (Integer number : intArray) {
            Image digit = SpriteLib.spriteImgNumbers(number, 2);
            ImageView digitView = new ImageView(digit);
            digitView.setSmooth(false);

            priceNumber.getChildren().add(digitView);
        }
        priceNumber.setLayoutY((parentHeight - tempHeight) / 2);
        priceNumber.setLayoutX(200);

        return priceNumber;
    }

    public void actualSwap(String choice, String tab) {
        switch (tab) {
            case "Seed":
                game.getPlayer().actualChosenSeed = choice;
                break;
            case "Animal":
                game.getPlayer().actualChosenAnimal = choice;
                break;
            case "Food":
                game.getPlayer().actualChosenFood = choice;
                break;
        }
    }

    public void createDragTab(Game game) {
        Pane pane = new Pane();
        double newTranslateY = 130;
        for (int i = 0; i < tabDrag.length; i++) {
            StackPane stackPane = new StackPane();
            stackPane.setId(tabDrag[i]);
            VBox vBox = new VBox();
            vBox.setSpacing(3);
            Image image = SpriteLib.spriteImg("BLANK", 3);
            ImageView imageView = new ImageView(image);
            imageView.setRotate(90);
            stackPane.setPrefSize(image.getHeight(), image.getWidth());
            stackPane.setTranslateX(45);
            stackPane.setTranslateY(newTranslateY);
            newTranslateY += 150;

            String[] letters = tabDrag[i].split("");

            double tempWidth = 0;
            double tempHeight = 0;

            for (String letter : letters) {
                Image letterImg = SpriteLib.spriteImgLetters(letter.toUpperCase(), 2);
                ImageView letterView = new ImageView(letterImg);
                letterView.setSmooth(false);
                tempWidth = letterImg.getWidth();
                tempHeight = letterImg.getHeight() + tempHeight;

                vBox.getChildren().add(letterView);
                letterView.setFitWidth(letterImg.getWidth());
            }
            vBox.setMouseTransparent(true);

            int finalI = i;
            imageView.setOnMousePressed(e -> {
                imageView.setImage(SpriteLib.spriteImg("CLICKBLANK", 3));
                for (int j = 0; j < myScrolls.length; j++) {
                    game.getGameBoard().lookup("#" + dragNDrop[j] + "tab").setVisible(false);
                }
                game.getGameBoard().lookup("#" + dragNDrop[finalI] + "tab").setVisible(true);
            });

            imageView.setOnMouseReleased(e -> {
                imageView.setImage(SpriteLib.spriteImg("BLANK", 3));
            });

            stackPane.getChildren().addAll(imageView, vBox);
            pane.getChildren().add(stackPane);
            vBox.setMaxSize(tempWidth, tempHeight);
            //vBox.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            //pane.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            //stackPane.setStyle("-fx-border-color: red; -fx-border-width: 2;");
            //imageView.setStyle("-fx-border-color: red; -fx-border-width: 2;");
        }
        game.getGameBoard().getChildren().add(pane);
    }

    public int getQuantity(String choice, String tabChoice) {
        ColorAdjust grayscale = new ColorAdjust();
        grayscale.setSaturation(-1.0);
        return switch (choice) {
            case "Seeds" -> game.getPlayer().seedMap.get(tabChoice).getQuantity();
            case "Animals" -> game.getPlayer().animalMap.get(tabChoice).getQuantity();
            case "Food" -> game.getPlayer().foodMap.get(tabChoice).getQuantity();
            default -> 0;
        };
    }
}
