package food;

public class PigFood extends Food{
    public double min = 10;
    public double max = 20;

    public PigFood(int quantity) {
        super(quantity);
        this.name = "PigFood";
        calcBasePrice(min, max);
    }
}
