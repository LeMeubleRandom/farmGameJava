package model;

import field.Field;
import field.SeedField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import seeds.CornSeed;

import java.io.Serializable;

public class Game implements Serializable {
    private transient StackPane gameBoardStack;
    private transient Pane gameBoard;
    private Player player;
    public Field[] fields = new Field[225];
    public SeedField[] seedFields = new SeedField[225];
    public transient Group[] groups = new Group[225];
    public String[] seedStarters = {"Carot", "Corn"};
    public String[] animalsStarters = {"Cow", "Hen"};

    public Game(StackPane newGameBoard, Pane gameBoard) {
        this.gameBoard = gameBoard;
        this.gameBoardStack = newGameBoard;
        player = new Player();
        for (String starter : seedStarters) {
            player.seedMap.get(starter).unlock(5);
            player.resourceMap.get(starter).unlock();
            player.unlockedSeed++;
        }

        for (String starter : animalsStarters) {
            player.animalMap.get(starter).unlock(1);
            player.resourceMap.get(starter).unlock();
            player.foodMap.get(starter).unlock(10);
            player.unlockedFood++;
            player.unlockedAnimal++;
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Pane getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Pane gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Field[] getFields() {
        return fields;
    }

    public void setFields(Field[] fields) {
        this.fields = fields;
    }

    public StackPane getGameBoardStack() {
        return gameBoardStack;
    }

    public void setGameBoardStack(StackPane gameBoardStack) {
        this.gameBoardStack = gameBoardStack;
    }
}
