package seeds;

public class LettuceSeed extends Seed {
    public double min = 60;
    public double max = 100;

    public LettuceSeed(int quantity) {
        super(quantity);
        this.name = "Lettuce";
        calcBasePrice(min, max);
    }
}
