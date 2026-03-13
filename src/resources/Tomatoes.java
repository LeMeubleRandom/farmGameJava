package resources;

public class Tomatoes extends Resources{
    public double min = 60;
    public double max = 100;

    public Tomatoes() {
        super();
        this.name = "Tomatoes";
        calcBasePrice(min, max);
    }
}
