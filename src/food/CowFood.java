package food;

public class CowFood extends Food{
    public double min = 10;
    public double max = 20;

    public CowFood(int quantity) {
        super(quantity);
        this.name = "CowFood";
        calcBasePrice(min, max);
    }
}
