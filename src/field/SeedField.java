package field;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import seeds.Seed;

import java.util.Objects;

public class SeedField extends Field {
    private String actualSeed;
    private String actualAnimal = "None";
    private boolean isReady = true;
    public boolean harvest = false;
    public boolean animalRend = false;
    public String state = "SeedField";
    public int generatedResource = 0;
    public int generatedMoney = 0;
    public int rendBySec = 5;
    public int maxGeneratedMoney = 500;

    public String getActualAnimal() {
        return actualAnimal;
    }

    public void setActualAnimal(String actualAnimal) {
        this.actualAnimal = actualAnimal;
    }

    public enum fieldType {
        SeedField, Enclosure, Building;
    }

    public SeedField(int newId) {
        super(newId);
        render();
    }

    public void fieldColor(Polygon top) {
        switch (state) {
            case "SeedField":
                top.setFill(Color.GREEN);
                break;
            case "Enclosure":
                top.setFill(Color.KHAKI);
                break;
            case "Building":
                top.setFill(Color.GREY);
                break;
        }
    }

    public void fieldOverColor(Polygon top) {
        switch (state) {
            case "SeedField":
                top.setFill(Color.DARKGREEN);
                break;
            case "Enclosure":
                top.setFill(Color.DARKKHAKI);
                break;
            case "Building":
                top.setFill(Color.DARKGREY);
                break;
        }
    }

    public void render() {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(2),
                e -> {
                    if (Objects.equals(state, "Building")) {
                        if (generatedMoney < maxGeneratedMoney) {
                            generatedMoney = generatedMoney + rendBySec;
                        }
                        else System.out.println("plein");
                    }
                });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void swapState(String newType) {
        state = newType;
    }

    public void setSeedField(String actualSeed) {
        this.actualSeed = actualSeed;
    }

    public String getActualSeed() {
        return actualSeed;
    }

    public void setActualSeed(String actualSeed) {
        this.actualSeed = actualSeed;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}
