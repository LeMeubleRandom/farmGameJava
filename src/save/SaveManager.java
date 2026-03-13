package save;

import model.Game;

import java.io.*;

public class SaveManager {

    private static final String SAVE_FILE = "farm_save.dat";

    public static void saveGame(Game game) {
        try (FileOutputStream fos = new FileOutputStream(SAVE_FILE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(game);
            System.out.println("Save in " + SAVE_FILE);

        } catch (IOException e) {
            System.err.println("Error");
            e.printStackTrace();
        }
    }
    public static Game loadGame() {
        try (FileInputStream fis = new FileInputStream(SAVE_FILE);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Game loadedGame = (Game) ois.readObject();
            System.out.println("Loaded");
            return loadedGame;

        } catch (FileNotFoundException e) {
            System.out.println("NONE");
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error");
            e.printStackTrace();
            return null;
        }
    }
}