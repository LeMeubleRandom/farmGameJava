package food;

public class HenFood extends Food{
    public double min = 10;
    public double max = 20;

    public HenFood(int quantity) {
        super(quantity);
        this.name = "HenFood";
        calcBasePrice(min, max);
    }
}
