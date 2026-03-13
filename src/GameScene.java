import controller.BoardController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.Game;
import save.SaveManager;

import java.io.Serializable;

public class GameScene {
    @FXML public StackPane gameBoardStack;
    public Pane gameBoard = new Pane();
    public Game game;
    public BoardController boardController;

    public void initialize() {
        new BackGround().loadImg(this);
        gameBoardStack.getChildren().add(gameBoard);
        game = new Game(gameBoardStack, gameBoard);
        boardController = new BoardController(game);
    }

    public void startGame(boolean loadSave) {
        if (loadSave) {
            Game savedGame = SaveManager.loadGame();
            if (savedGame != null) {
                this.game = savedGame;
                this.game.setGameBoardStack(gameBoardStack);
                this.game.setGameBoard(gameBoard);
            } else {
                this.game = new Game(gameBoardStack, gameBoard);
            }
        } else {
            this.game = new Game(gameBoardStack, gameBoard);
        }
        boardController = new BoardController(game);
    }
}
