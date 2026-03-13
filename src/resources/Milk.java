package resources;

public class Milk extends Resources{
    public double min = 60;
    public double max = 100;

    public Milk() {
        super();
        this.name = "Milk";
        calcBasePrice(min, max);
    }
}
