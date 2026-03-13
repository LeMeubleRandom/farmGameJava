package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SpriteLib {
    private static final Map<ButtonType, Rectangle2D> buttonCoords = new HashMap<>();
    private static final Map<Alphabet, Rectangle2D> letterCoords = new HashMap<>();
    private static final Map<Alphabet2, Rectangle2D> letterBisCoords = new HashMap<>();
    private static final Map<Integer, Rectangle2D> numberCoords = new HashMap<>();
    private static final Map<Inventory, Rectangle2D> inventoryCoords = new HashMap<>();
    private static final Map<Tiles, Rectangle2D> tilesCoords = new HashMap<>();
    public static Image text = new Image(Objects.requireNonNull(SpriteLib.class.getResourceAsStream("/assets/ui/PNG/Text1.png")));
    public static Image buttons = new Image(Objects.requireNonNull(SpriteLib.class.getResourceAsStream("/assets/ui/PNG/Buttons.png")));
    public static Image inventory = new Image(Objects.requireNonNull(SpriteLib.class.getResourceAsStream("/assets/ui/PNG/Inventory.png")));
    public static Image tiles = new Image(Objects.requireNonNull(SpriteLib.class.getResourceAsStream("/assets/ui/PNG/Main_tiles.png")));

    public enum ButtonType {
        RESUME, RESTART, QUIT, SHOP, INVENTORY, SAVE, ONRESUME, ONRESTART, ONQUIT, ONSHOP, ONINVENTORY, ONSAVE, CLICKRESUME, CLICKRESTART, CLICKQUIT, CLICKSHOP, CLICKINVENTORY, CLICKSAVE, BLANK, ONBLANK, CLICKBLANK;
    }

    public enum Alphabet {
        A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V ,W, X, Y, Z, TWOPOINTS,ESPACE;
    }

    public enum Alphabet2 {
        A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V ,W, X, Y, Z, TWOPOINTS,ESPACE;
    }

    public enum Inventory {
        EMPTY, CASE ;
    }

    public enum Tiles {
        BLANK, GREENBLANK, BIGCASE, FILLBIGCASE;
    }

    static {
        buttonCoords.put(ButtonType.RESUME, new Rectangle2D(173, 290, 56, 12));
        buttonCoords.put(ButtonType.RESTART, new Rectangle2D(173, 306, 56, 12));
        buttonCoords.put(ButtonType.QUIT, new Rectangle2D(173, 418, 56, 12));
        buttonCoords.put(ButtonType.SHOP, new Rectangle2D(173, 386, 56, 12));
        buttonCoords.put(ButtonType.INVENTORY, new Rectangle2D(173, 354, 56, 12));
        buttonCoords.put(ButtonType.SAVE, new Rectangle2D(308, 258, 39, 13));
        buttonCoords.put(ButtonType.ONRESUME, new Rectangle2D(253, 290, 56, 12));
        buttonCoords.put(ButtonType.ONRESTART, new Rectangle2D(253, 306, 56, 12));
        buttonCoords.put(ButtonType.ONQUIT, new Rectangle2D(253, 418, 56, 12));
        buttonCoords.put(ButtonType.ONSHOP, new Rectangle2D(253, 386, 56, 12));
        buttonCoords.put(ButtonType.ONINVENTORY, new Rectangle2D(253, 354, 56, 12));
        buttonCoords.put(ButtonType.ONSAVE, new Rectangle2D(356, 259, 39, 13));
        buttonCoords.put(ButtonType.CLICKRESUME, new Rectangle2D(93, 274, 56, 12));
        buttonCoords.put(ButtonType.CLICKRESTART, new Rectangle2D(93, 290, 56, 12));
        buttonCoords.put(ButtonType.CLICKQUIT, new Rectangle2D(93, 402, 56, 12));
        buttonCoords.put(ButtonType.CLICKSHOP, new Rectangle2D(93, 370, 56, 12));
        buttonCoords.put(ButtonType.CLICKINVENTORY, new Rectangle2D(93, 338, 56, 12));
        buttonCoords.put(ButtonType.CLICKSAVE, new Rectangle2D(260, 259, 39, 13));
        buttonCoords.put(ButtonType.BLANK, new Rectangle2D(99,96,42, 16));
        buttonCoords.put(ButtonType.ONBLANK, new Rectangle2D(147, 96, 42, 16));
        buttonCoords.put(ButtonType.CLICKBLANK, new Rectangle2D(51, 96, 42, 16));

        //+7x  +9y entre chaque colonne et ligne de lettre
        letterCoords.put(Alphabet.A, new Rectangle2D(1, 1, 5, 7));
        letterCoords.put(Alphabet.B, new Rectangle2D(8, 1, 5, 7));
        letterCoords.put(Alphabet.C, new Rectangle2D(15,1, 5, 7));
        letterCoords.put(Alphabet.D, new Rectangle2D(22,1, 5, 7));
        letterCoords.put(Alphabet.E, new Rectangle2D(29,1, 5, 7));
        letterCoords.put(Alphabet.F, new Rectangle2D(36,1, 5, 7));
        letterCoords.put(Alphabet.G, new Rectangle2D(43,1, 5, 7));
        letterCoords.put(Alphabet.H, new Rectangle2D(50,1, 5, 7));
        letterCoords.put(Alphabet.I, new Rectangle2D(57,1, 5, 7));
        letterCoords.put(Alphabet.J, new Rectangle2D(64,1, 5, 7));
        letterCoords.put(Alphabet.K, new Rectangle2D(1,  10, 5, 7));
        letterCoords.put(Alphabet.L, new Rectangle2D(8,  10, 5, 7));
        letterCoords.put(Alphabet.M, new Rectangle2D(15, 10, 6, 7));
        letterCoords.put(Alphabet.N, new Rectangle2D(22, 10, 6, 7));
        letterCoords.put(Alphabet.O, new Rectangle2D(29, 10, 5, 7));
        letterCoords.put(Alphabet.P, new Rectangle2D(36, 10, 5, 7));
        letterCoords.put(Alphabet.Q, new Rectangle2D(43, 10, 5, 7));
        letterCoords.put(Alphabet.R, new Rectangle2D(50, 10, 5, 7));
        letterCoords.put(Alphabet.S, new Rectangle2D(57, 10, 5, 7));
        letterCoords.put(Alphabet.T, new Rectangle2D(64, 10, 5, 7));
        letterCoords.put(Alphabet.U, new Rectangle2D(1, 19, 5, 7));
        letterCoords.put(Alphabet.V, new Rectangle2D(8, 19, 5, 7));
        letterCoords.put(Alphabet.W, new Rectangle2D(15, 19, 6, 7));
        letterCoords.put(Alphabet.X, new Rectangle2D(22, 19, 5, 7));
        letterCoords.put(Alphabet.Y, new Rectangle2D(29, 19, 5, 7));
        letterCoords.put(Alphabet.Z, new Rectangle2D(36, 19, 5, 7));
        letterCoords.put(Alphabet.TWOPOINTS, new Rectangle2D(15,37,5,7));
        letterCoords.put(Alphabet.ESPACE, new Rectangle2D(43, 19, 5, 7));

        letterBisCoords.put(Alphabet2.A, new Rectangle2D(1, 277, 5, 8));
        letterBisCoords.put(Alphabet2.B, new Rectangle2D(8, 277, 5, 8));
        letterBisCoords.put(Alphabet2.C, new Rectangle2D(15, 277, 5, 8));
        letterBisCoords.put(Alphabet2.D, new Rectangle2D(22, 277, 5, 8));
        letterBisCoords.put(Alphabet2.E, new Rectangle2D(29, 277, 5, 8));
        letterBisCoords.put(Alphabet2.F, new Rectangle2D(36, 277, 5, 8));
        letterBisCoords.put(Alphabet2.G, new Rectangle2D(43, 277, 5, 8));
        letterBisCoords.put(Alphabet2.H, new Rectangle2D(50, 277, 5, 8));
        letterBisCoords.put(Alphabet2.I, new Rectangle2D(57, 277, 5, 8));
        letterBisCoords.put(Alphabet2.J, new Rectangle2D(64, 277, 5, 8));
        letterBisCoords.put(Alphabet2.K, new Rectangle2D(1,  286, 5, 8));
        letterBisCoords.put(Alphabet2.L, new Rectangle2D(8,  286, 5, 8));
        letterBisCoords.put(Alphabet2.M, new Rectangle2D(15, 286, 6, 8));
        letterBisCoords.put(Alphabet2.N, new Rectangle2D(22, 286, 6, 8));
        letterBisCoords.put(Alphabet2.O, new Rectangle2D(29, 286, 5, 8));
        letterBisCoords.put(Alphabet2.P, new Rectangle2D(36, 286, 5, 8));
        letterBisCoords.put(Alphabet2.Q, new Rectangle2D(43, 286, 5, 8));
        letterBisCoords.put(Alphabet2.R, new Rectangle2D(50, 286, 5, 8));
        letterBisCoords.put(Alphabet2.S, new Rectangle2D(57, 286, 5, 8));
        letterBisCoords.put(Alphabet2.T, new Rectangle2D(64, 286, 5, 8));
        letterBisCoords.put(Alphabet2.U, new Rectangle2D(1, 295, 5, 8));
        letterBisCoords.put(Alphabet2.V, new Rectangle2D(8, 295, 5, 8));
        letterBisCoords.put(Alphabet2.W, new Rectangle2D(15, 295, 6, 8));
        letterBisCoords.put(Alphabet2.X, new Rectangle2D(22, 295, 5, 8));
        letterBisCoords.put(Alphabet2.Y, new Rectangle2D(29, 295, 5, 8));
        letterBisCoords.put(Alphabet2.Z, new Rectangle2D(36, 295, 5, 8));
        letterBisCoords.put(Alphabet2.TWOPOINTS, new Rectangle2D(15,313,5,8));
        letterBisCoords.put(Alphabet2.ESPACE, new Rectangle2D(43, 295, 5, 8));

        numberCoords.put(1, new Rectangle2D(1, 304, 5 , 8));
        numberCoords.put(2, new Rectangle2D(8, 304, 5 , 8));
        numberCoords.put(3, new Rectangle2D(15, 304, 5, 8));
        numberCoords.put(4, new Rectangle2D(22, 304, 5, 8));
        numberCoords.put(5, new Rectangle2D(29, 304, 5, 8));
        numberCoords.put(6, new Rectangle2D(36, 304, 5, 8));
        numberCoords.put(7, new Rectangle2D(43, 304, 5, 8));
        numberCoords.put(8, new Rectangle2D(50, 304, 5, 8));
        numberCoords.put(9, new Rectangle2D(57, 304, 5, 8));
        numberCoords.put(0, new Rectangle2D(64, 304, 5, 8));

        inventoryCoords.put(Inventory.EMPTY, new Rectangle2D(7, 0, 98, 101));
        inventoryCoords.put(Inventory.CASE, new Rectangle2D(119, 0, 98, 101));

        tilesCoords.put(Tiles.BIGCASE, new Rectangle2D(208, 196, 48, 40));
        tilesCoords.put(Tiles.FILLBIGCASE, new Rectangle2D(103, 203, 34, 26));
    }

    public static Rectangle2D getCoords(ButtonType type) {
        return buttonCoords.get(type);
    }
    public static Rectangle2D getCoordsLetter(Alphabet type) {
        return letterCoords.get(type);
    }
    public static Rectangle2D getCoordsLetterBis(Alphabet2 type) {
        return letterBisCoords.get(type);
    }
    public static Rectangle2D getCoordsNumber(Integer type) {
        return numberCoords.get(type);
    }
    public static Rectangle2D getCoordsInventory(Inventory type) {
        return inventoryCoords.get(type);
    }
    public static Rectangle2D getCoordsTiles(Tiles type) {
        return tilesCoords.get(type);
    }

    public static Image getUpscaledImage(Image source, int scale) {
        int width = (int) source.getWidth();
        int height = (int) source.getHeight();

        WritableImage upscaled = new WritableImage(width * scale, height * scale);
        PixelReader reader = source.getPixelReader();
        PixelWriter writer = upscaled.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = reader.getArgb(x, y);
                for (int dy = 0; dy < scale; dy++) {
                    for (int dx = 0; dx < scale; dx++) {
                        writer.setArgb(x * scale + dx, y * scale + dy, argb);
                    }
                }
            }
        }
        return upscaled;
    }

    public static Image spriteImgMoney(int scale) {
        Rectangle2D rect = new Rectangle2D(483, 163, 10, 10);
        Image image = new Image("/assets/ui/PNG/Shop.png");
        PixelReader reader = image.getPixelReader();
        WritableImage cropped = new WritableImage(
                reader,
                (int) rect.getMinX(),
                (int) rect.getMinY(),
                (int) rect.getWidth(),
                (int) rect.getHeight()
        );

        return getUpscaledImage(cropped, scale);
    }

    public static Image spriteImgLetters(String letterType, int scale) {
        PixelReader reader = text.getPixelReader();
        Rectangle2D rect = getCoordsLetter(Alphabet.valueOf(letterType));
        WritableImage cropped = new WritableImage(
                reader,
                (int) rect.getMinX(),
                (int) rect.getMinY(),
                (int) rect.getWidth(),
                (int) rect.getHeight()
        );

        return getUpscaledImage(cropped, scale);
    }

    public static Image spriteImgLettersBis(String LetterType, int scale) {
        PixelReader reader = text.getPixelReader();
        Rectangle2D rect = getCoordsLetterBis(Alphabet2.valueOf(LetterType));
        WritableImage cropped = new WritableImage(
                reader,
                (int) rect.getMinX(),
                (int) rect.getMinY(),
                (int) rect.getWidth(),
                (int) rect.getHeight()
        );

        return getUpscaledImage(cropped, scale);
    }

    public static Image spriteImgNumbers(Integer NumberType, int scale) {
        PixelReader reader = text.getPixelReader();
        Rectangle2D rect = getCoordsNumber(NumberType);
        WritableImage cropped = new WritableImage(
                reader,
                (int) rect.getMinX(),
                (int) rect.getMinY(),
                (int) rect.getWidth(),
                (int) rect.getHeight()
        );

        return getUpscaledImage(cropped, scale);
    }

    public static Image spriteImgInventory(String inventoryType, int scale) {
        PixelReader reader = inventory.getPixelReader();
        Rectangle2D rect = getCoordsInventory(Inventory.valueOf(inventoryType));
        WritableImage cropped = new WritableImage(
                reader,
                (int) rect.getMinX(),
                (int) rect.getMinY(),
                (int) rect.getWidth(),
                (int) rect.getHeight()
        );

        return getUpscaledImage(cropped, scale);
    }

    public static Image spriteImgTiles(String tilesType, int scale) {
        PixelReader reader = tiles.getPixelReader();
        Rectangle2D rect = getCoordsTiles(Tiles.valueOf(tilesType));
        WritableImage cropped = new WritableImage(
                reader,
                (int) rect.getMinX(),
                (int) rect.getMinY(),
                (int) rect.getWidth(),
                (int) rect.getHeight()
        );

        return getUpscaledImage(cropped, scale);
    }

    public static Image spriteImg(String btnType, int scale) {
        PixelReader reader = buttons.getPixelReader();
        Rectangle2D rect = getCoords(ButtonType.valueOf(btnType));
        WritableImage cropped = new WritableImage(
                reader,
                (int) rect.getMinX(),
                (int) rect.getMinY(),
                (int) rect.getWidth(),
                (int) rect.getHeight()
        );

        return getUpscaledImage(cropped, scale);
    }

    public static Integer[] splitNumber(String intArray){
        Integer[] numbers = new Integer[intArray.length()];

        for (int i = 0; i < intArray.length(); i++) {
            numbers[i] = Character.getNumericValue(intArray.charAt(i));
        }

        return numbers;
    }
}
