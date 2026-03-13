package resources;

public class Wool extends Resources{
    public double min = 60;
    public double max = 100;

    public Wool() {
        super();
        this.name = "Wool";
        calcBasePrice(min, max);
    }
}
