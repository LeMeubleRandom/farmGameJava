package seeds;

public class TomatoesSeed extends Seed {
    public double min = 60;
    public double max = 100;

    public TomatoesSeed(int quantity) {
        super(quantity);
        this.name = "Tomato";
        calcBasePrice(min, max);
    }
}
