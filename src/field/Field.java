package field;

import model.Game;
import model.Money;
import model.Player;

import java.io.Serializable;

public abstract class Field implements Serializable {
    private int price;
    private int id;
    private int level;
    public boolean isUnlock = false;
    private int fieldId;
    public int buildingPrice;
    public int enclosurePrice;

    public Field(int newId) {
        this.level = 1;
        this.fieldId = newId;
        this.price = 100 * fieldId;
        this.buildingPrice = 100 * fieldId * 5;
    }

    public boolean buyField(Game game) {
        if (game.getPlayer().getMoney() >= getPrice()) {
            int newMoneyCount = game.getPlayer().getMoney() - getPrice();
            game.getPlayer().setMoney(newMoneyCount);
            Money.createMoney(game);
            return true;
        } else return false;
    }

    public void levelUp() {
        this.level++;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }
}
