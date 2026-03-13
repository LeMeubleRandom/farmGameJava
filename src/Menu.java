import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.SpriteLib;

import java.io.IOException;

public class Menu {
    @FXML public transient StackPane MenuFX;
    public transient Pane UnderPane;
    public String[] menu = {"RESUME", "RESTART", "QUIT"};
    public String[] menuHover = {"ONRESUME", "ONRESTART", "ONQUIT"};
    public String[] menuClick = {"CLICKRESUME", "CLICKRESTART", "CLICKQUIT"};
    Image buttons;
    Image letters;
    Image tiles;

    public void initialize() {;
        buttons = new Image(getClass().getResourceAsStream("/assets/ui/PNG/Buttons.png"));
        letters = new Image(getClass().getResourceAsStream("/assets/ui/PNG/Text1.png"));
        tiles = new Image(getClass().getResourceAsStream("/assets/ui/PNG/Main_tiles.png"));
        UnderPane = new Pane();

        int folderIndex = (int) ((((3 - 1) + 1) * Math.random()) + 1);
        Image backGround = new Image(getClass().getResourceAsStream("/assets/bg2/" + folderIndex + "/0.png"));
        ImageView imgv = new ImageView(backGround);
        imgv.setPreserveRatio(true);
        imgv.setSmooth(true);
        imgv.fitHeightProperty().bind(MenuFX.heightProperty());

        createTitle();

        MenuFX.getChildren().addAll(imgv, UnderPane);
        createMenu();
    }

    @FXML public void launchGame(boolean save) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./jfx/gameScene.fxml"));
        Parent root = loader.load();

        GameScene gameSceneController = loader.getController();
        gameSceneController.startGame(save);

        Stage stage = (Stage) MenuFX.getScene().getWindow();
        Scene scene = new Scene(root, 1280, 720);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void createTitle() {
        Pane pane = new Pane();

        String[] title = {"F", "A", "R", "M", "ESPACE", "M", "Y", "ESPACE", "F", "A", "R", "M"};
        int width = 0;
        for (int i = 0; i < title.length; i++) {
            Image image = SpriteLib.spriteImgLettersBis(title[i], 3);
            ImageView imageView = new ImageView(image);
            imageView.setSmooth(false);
            //width = width + image.getWidth();

            imageView.setLayoutX(i * 21);
            imageView.setLayoutY(200);

            pane.getChildren().add(imageView);
        }
        pane.setLayoutX((500 - 234) / 2);
        UnderPane.getChildren().add(pane);
    }

    public void createMenu() {
        for (int i = 0; i < menu.length; i++) {
            Image image = SpriteLib.spriteImg(menu[i], 3);
            ImageView imageView = new ImageView(image);
            imageView.setSmooth(false);

            int finalI = i;
            imageView.setOnMousePressed(e -> {
                imageView.setImage(SpriteLib.spriteImg(menuClick[finalI], 3));
            });

            imageView.setOnMouseReleased(e -> {
                imageView.setImage(SpriteLib.spriteImg(menu[finalI], 3));
                menuChoice(menuClick[finalI]);
            });

            imageView.setOnMouseEntered(e -> {
                imageView.setImage(SpriteLib.spriteImg(menuHover[finalI], 3));
            });

            imageView.setOnMouseExited(e -> {
                imageView.setImage(SpriteLib.spriteImg(menu[finalI], 3));
            });

            imageView.setLayoutX((500 - image.getWidth()) / 2);
            imageView.setLayoutY(70 * (i + 1) + 300);

            UnderPane.getChildren().add(imageView);
        }
    }

    public void menuChoice(String btnType) {
        if (btnType == "CLICKRESUME") {
            try  {
                launchGame(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btnType == "CLICKRESTART") {
            try  {
                launchGame(false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btnType == "CLICKQUIT") {
            Stage stage = (Stage) MenuFX.getScene().getWindow();
            stage.close();
        }
    }
}
