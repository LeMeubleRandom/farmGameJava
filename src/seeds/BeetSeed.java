package seeds;

public class BeetSeed extends Seed{
    public double min = 60;
    public double max = 100;

    public BeetSeed(int quantity) {
        super(quantity);
        this.name = "Beet";
        calcBasePrice(min, max);
    }
}
