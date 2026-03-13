import animals.Animals;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model.Game;
import model.Show;

import java.util.concurrent.atomic.AtomicInteger;

public class BackGround {
    private ImageView sky = new ImageView();
    private ImageView cloud = new ImageView();
    private ImageView cloud1 = new ImageView();
    private ImageView cloud2 = new ImageView();

    public void loadImg(GameScene root) {
        AtomicInteger skyState = new AtomicInteger(1);
        setupImage(sky, root.gameBoardStack);
        setupImage(cloud, root.gameBoardStack);
        setupImage(cloud1, root.gameBoardStack);
        setupImage(cloud2, root.gameBoardStack);
        root.gameBoardStack.getChildren().addAll(sky, cloud, cloud1, cloud2);

        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(60),
                e -> {
                    int nextState = skyState.incrementAndGet();
                    if (nextState > 8) {
                        skyState.set(1);
                        nextState = 1;
                    }
                    Show.shopState = false;
                    Show.inventoryState = false;
                    Node shopNode = root.game.getGameBoard().lookup("#Shop");
                    Node inventoryNode = root.game.getGameBoard().lookup("#Inventory");
                    Node overlayNode = root.game.getGameBoard().lookup("#Overlay");
                    root.game.getGameBoard().getChildren().remove(shopNode);
                    root.game.getGameBoard().getChildren().remove(inventoryNode);
                    root.game.getGameBoard().getChildren().remove(overlayNode);
                    root.game.getGameBoard().lookup("#text_box").setVisible(true);
                    swapPrice(root.game);
                    updateBackGround(nextState);
                }
        );
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        updateBackGround(skyState.get());
    }

    public void swapPrice(Game game) {
        game.getPlayer().animalMap.forEach((name, animal) -> {
            animal.calcPrice(0, true);
        });
        game.getPlayer().resourceMap.forEach((name, resource) -> {
            resource.calcPrice(0, true);
        });
        game.getPlayer().seedMap.forEach((name, seed) -> {
            seed.calcPrice(0, true);
        });
    }

    public void setupImage(ImageView iv, StackPane root) {
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.fitWidthProperty().bind(root.widthProperty());
        iv.fitHeightProperty().bind(root.heightProperty());
    }

    public void updateBackGround(int folderIndex) {
        String path = "assets/bg/Clouds/Clouds " + folderIndex + "/";
        sky.setImage(new Image(path + "1.png"));
        cloud.setImage(new Image(path + "2.png"));
        cloud1.setImage(new Image(path + "3.png"));
        cloud2.setImage(new Image(path + "4.png"));
    }
}
