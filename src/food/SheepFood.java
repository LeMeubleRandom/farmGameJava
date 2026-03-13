package food;

public class SheepFood extends Food{
    public double min = 10;
    public double max = 20;

    public SheepFood(int quantity) {
        super(quantity);
        this.name = "SheepFood";
        calcBasePrice(min, max);
    }
}
