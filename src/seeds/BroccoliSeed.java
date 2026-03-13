package seeds;

public class BroccoliSeed extends Seed{
    public double min = 60;
    public double max = 100;

    public BroccoliSeed(int quantity) {
        super(quantity);
        this.name = "Broccoli";
        calcBasePrice(min, max);
    }
}
