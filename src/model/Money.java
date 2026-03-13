package model;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Money {
    public static void createMoney(Game game) {
        Node oldSharpView = game.getGameBoard().lookup("#moneyCount");
        if (oldSharpView != null) ((Pane) oldSharpView.getParent()).getChildren().remove(oldSharpView);
        StackPane stackPane = new StackPane();
        stackPane.setId("moneyCount");

        Image moneyImg = SpriteLib.spriteImg("ONBLANK", 4);
        ImageView moneyView = new ImageView(moneyImg);

        HBox textBox = new HBox();
        textBox.setSpacing(7);
        String money = String.valueOf(game.getPlayer().getMoney());
        Integer[] moneyArray = SpriteLib.splitNumber(money);

        Image image = SpriteLib.spriteImgMoney(2);
        ImageView imageView = new ImageView(image);

        Image espace = SpriteLib.spriteImgLetters("ESPACE", 2);
        ImageView espaceView = new ImageView(espace);
        textBox.getChildren().addAll(imageView, espaceView);

        for (Integer number : moneyArray) {
            Image digit = SpriteLib.spriteImgNumbers(number, 2);
            ImageView digitView = new ImageView(digit);
            digitView.setSmooth(false);
            digitView.setFitHeight(digit.getHeight() + 6);
            digitView.setFitWidth(digit.getWidth() + 2);

            textBox.getChildren().add(digitView);
        }
        stackPane.getChildren().addAll(moneyView, textBox);
        game.getGameBoard().getChildren().add(stackPane);
        stackPane.setMaxSize(moneyImg.getWidth(),moneyImg.getHeight());
        textBox.setMaxSize(0, 0);
    }
}
