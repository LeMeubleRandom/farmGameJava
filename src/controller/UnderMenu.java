package controller;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class UnderMenu {
    private String[] onglets = {"Seed", "Resources", "Animals"};

    public void createInventory() {
        ScrollPane scrollPane = new ScrollPane();
        HBox container = new HBox();
        container.setSpacing(5);
        container.setPadding(new Insets(5));
        ColorAdjust grayscale = new ColorAdjust();
        grayscale.setSaturation(-1.0);
        StackPane onglet = new StackPane();


    }

    public void createShop() {

    }
}
