package resources;

public class Potatoes extends Resources{
    public double min = 60;
    public double max = 100;

    public Potatoes() {
        super();
        this.name = "Potatoes";
        calcBasePrice(min, max);
    }
}
