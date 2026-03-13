package seeds;

public class RadishSeed extends Seed {
    public double min = 60;
    public double max = 100;

    public RadishSeed(int quantity) {
        super(quantity);
        this.name = "Radish";
        calcBasePrice(min, max);
    }
}
