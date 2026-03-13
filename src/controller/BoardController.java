package controller;

import field.SeedField;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import model.Game;
import model.Player;
import model.Show;

import java.io.Serializable;

public class BoardController implements Serializable {
    private Game game;
    public Show show;
    private Player player;
    private SeedField[] emptySeedFields;
    private int gridCols = 15;
    private int gridRows = 15;
    private double tileWidth = 64.0;
    private double tileHeight = 32.0;

    private double totalMapWidth = (gridCols + gridRows) * (tileWidth / 2.0);
    private double totalMapHeight = (gridCols + gridRows) * (tileHeight / 2.0);

    private double offsetX = (1280 - totalMapWidth) + 320;
    private double offsetY = (720 - totalMapHeight) - 50;

    private double thickness = 20;

    public BoardController(Game game) {
        this.game = game;
        createBoard();
        show = new Show(this.game);
        show.buildInterface();
        show.buildShop();
        show.buildInventory();
    }

    public void createBoard() {
        int fieldId = 0;
        for (int x = 0; x < gridCols; x++) {
            for (int y = 0; y < gridRows; y++) {
                double screenX = (x - y) * (tileWidth / 2.0) + offsetX;
                double screenY = (x + y) * (tileHeight / 2.0) + offsetY;

                Group block = createIsometricBlock(screenX + 100, screenY, fieldId);
                game.getGameBoard().getChildren().add(block);

                fieldId++;
            }
        }
    }

    private Group createIsometricBlock(double x, double y, int fieldId) {
        SeedField seedField = new SeedField(fieldId);
        game.fields[fieldId] = seedField;
        game.seedFields[fieldId] = seedField;

        Polygon top = new Polygon(
                x, y,
                (x + tileWidth / 2), (y + tileHeight / 2),
                x,( y + tileHeight),
                (x - tileWidth / 2), ( y + tileHeight / 2)
        );
        top.setOpacity(0.5);
        top.setFill(Color.LIGHTBLUE);
        top.setStroke(Color.GREY);
        top.setId("Top_Tile_" + fieldId);

        Polygon left = new Polygon(
                (x - tileWidth / 2), (y + tileHeight / 2),
                x, (y + tileHeight),
                x, (y + tileHeight + thickness),
                (x - tileWidth / 2), (y + tileHeight / 2 + thickness)
        );
        left.setOpacity(0.5);
        left.setFill(Color.LIGHTBLUE);
        left.setStroke(Color.GREY);
        left.setId("Left_Tile_" + fieldId);

        Polygon right = new Polygon(
                x, (y + tileHeight),
                (x + tileWidth / 2), (y + tileHeight / 2),
                (x + tileWidth / 2), (y + tileHeight / 2 + thickness),
                x, (y + tileHeight + thickness)
        );
        right.setOpacity(0.5);
        right.setFill(Color.LIGHTBLUE);
        right.setStroke(Color.GREY);
        right.setId("Right_Tile_" + fieldId);

        Group group = new Group(left, right, top);
        game.groups[fieldId] = group;

        FieldController.setMouseEffect(game, fieldId);

        return group;
    }
}
