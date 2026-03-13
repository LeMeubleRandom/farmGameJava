package seeds;

public class PotatoesSeed extends Seed {
    public double min = 60;
    public double max = 100;

    public PotatoesSeed(int quantity) {
        super(quantity);
        this.name = "Potato";
        calcBasePrice(min, max);
    }
}
