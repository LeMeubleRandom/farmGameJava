package seeds;

public class CornSeed extends Seed{
    public double min = 60;
    public double max = 100;

    public CornSeed(int quantity) {
        super(quantity);
        this.name = "Corn";
        calcBasePrice(min, max);
    }
}
