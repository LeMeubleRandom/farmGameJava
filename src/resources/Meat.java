package resources;

public class Meat extends Resources{
    public double min = 60;
    public double max = 100;

    public Meat() {
        super();
        this.name = "Meat";
        calcBasePrice(min, max);
    }
}
