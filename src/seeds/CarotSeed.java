package seeds;

public class CarotSeed extends Seed{
    public double min = 60;
    public double max = 100;

    public CarotSeed(int quantity) {
        super(quantity);
        this.name = "Carot";
        calcBasePrice(min, max);
    }

    public String getName() {
        return name;
    }
}
