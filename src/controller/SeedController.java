package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Game;
import model.SpriteLib;
import seeds.Seed;
import javafx.scene.shape.Polygon;

import java.awt.*;
import java.sql.Time;
import java.util.concurrent.atomic.AtomicInteger;

public class SeedController {

    public static String[] drags = {"Animals", "Food", "Seeds"};

    public static void plantSeed(Game game, int fieldId, String seedName) {
        game.seedFields[fieldId].setReady(false);
        Seed seed = game.getPlayer().seedMap.get(seedName);
        seed.setQuantity(seed.getQuantity() - 1);

        HBox textBox = (HBox) game.getGameBoard().lookup("#text_box");
        textBox.getChildren().clear();

        String count = String.valueOf(game.getPlayer().seedMap.get(seedName).getQuantity());
        Integer[] intArray = SpriteLib.splitNumber(count);
        for (int j = 0; j < count.length(); j++) {
            Image image = SpriteLib.spriteImgNumbers(intArray[j], 2);
            ImageView imageView = new ImageView(image);
            imageView.setSmooth(false);

            textBox.getChildren().add(imageView);
        }

        javafx.scene.shape.Rectangle bgBar = new javafx.scene.shape.Rectangle(50, 8, Color.DARKSLATEGRAY);
        bgBar.setStroke(Color.BLACK);
        bgBar.setStrokeWidth(1.5);
        javafx.scene.shape.Rectangle fillBar = new javafx.scene.shape.Rectangle(0, 8, Color.LIMEGREEN);
        javafx.scene.layout.StackPane barContainer = new javafx.scene.layout.StackPane(bgBar, fillBar);
        barContainer.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        barContainer.setTranslateX(15);
        barContainer.setTranslateY(20);
        game.groups[fieldId].getChildren().add(barContainer);

        Timeline timeline = new Timeline();
        AtomicInteger growthLevel = new AtomicInteger();
        KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(10),
                e -> {
                    growthLevel.getAndIncrement();
                });

        timeline.getKeyFrames().add(keyFrame);
        Timeline progressBar = new Timeline(
                new KeyFrame(Duration.ZERO, new javafx.animation.KeyValue(fillBar.widthProperty(), 0)),
                new KeyFrame(Duration.seconds(40), new javafx.animation.KeyValue(fillBar.widthProperty(), 50))
        );
        timeline.setCycleCount(4);
        timeline.setOnFinished(e -> {
            game.seedFields[fieldId].harvest = true;
            game.groups[fieldId].getChildren().remove(barContainer);
        });
        barContainer.toFront();
        barContainer.setTranslateY(-100);
        timeline.play();
        progressBar.play();
    }

    public static void feed(Game game, int fieldId, String foodName) {
        int quantity = game.getPlayer().foodMap.get(foodName).getQuantity();
        game.getPlayer().foodMap.get(foodName).setQuantity(quantity - 1);
        HBox textBox = (HBox) game.getGameBoard().lookup("#text_box");
        textBox.getChildren().clear();

        String count = String.valueOf(game.getPlayer().foodMap.get(foodName).getQuantity());
        Integer[] intArray = SpriteLib.splitNumber(count);
        for (int j = 0; j < count.length(); j++) {
            Image image = SpriteLib.spriteImgNumbers(intArray[j], 2);
            ImageView imageView = new ImageView(image);
            imageView.setSmooth(false);

            textBox.getChildren().add(imageView);
        }

        Polygon top = (Polygon) game.groups[fieldId].lookup("#Top_Tile_" + fieldId);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(5),
                e -> {
                    String animalName = game.seedFields[fieldId].getActualAnimal();
                    game.seedFields[fieldId].animalRend = true;
                    top.setFill(Color.WHITE);
                    if (game.getPlayer().foodMap.get(foodName).getQuantity() <= 0) {
                        top.setFill(Color.KHAKI);
                        game.seedFields[fieldId].setActualAnimal("None");
                        game.seedFields[fieldId].animalRend = false;
                        game.seedFields[fieldId].generatedResource = 0;
                        int tempQuantity = game.getPlayer().animalMap.get(foodName).getQuantity();
                        game.getPlayer().animalMap.get(foodName).setQuantity(tempQuantity - 1);
                        int inEnclos = game.getPlayer().animalMap.get(foodName).inEnclos;
                        game.getPlayer().animalMap.get(foodName).inEnclos = inEnclos - 1;
                    }
                    System.out.println(animalName);
                    game.getPlayer().animalMap.get(animalName).generateResources(game);
                });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(3);
        timeline.setOnFinished(e -> {
            top.setFill(Color.DARKKHAKI);
        });
        timeline.play();
    }
}
