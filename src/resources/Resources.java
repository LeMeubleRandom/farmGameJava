package resources;

import java.io.Serializable;

public abstract class Resources implements Serializable {
    public String name;
    private int quantity = 0;
    private boolean isUnlock = false;
    private int basePrice;
    private int price;

    public void calcBasePrice(double min, double max) {
        double range = max - min + 1;
        double tempNumber = (double) Math.round(((Math.random() * range) + min) * 100) / 100;
        basePrice = (int) Math.round(tempNumber);
        calcPrice(0, true);
    }

    public double tauxCalc() {
        double min = -30;
        double max = 30;
        double range = max - min + 1;
        double randNum = Math.random() * range + min;
        return (double) Math.round(randNum) / 100;
    }

    public void calcPrice(double taux, boolean autoTaux) {
        if (autoTaux) taux = tauxCalc();
        double tempNumber = (double) Math.round((basePrice + (basePrice * taux)) * 100) / 100;
        price = (int) Math.round(tempNumber);
    }

    public boolean isUnlock() {
        return isUnlock;
    }

    public void unlock() {
        isUnlock = true ;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
