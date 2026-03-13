package seeds;

public class PeasSeed extends Seed{
    public double min = 60;
    public double max = 100;

    public PeasSeed(int quantity) {
        super(quantity);
        this.name = "Peas";
        calcBasePrice(min, max);
    }
}
