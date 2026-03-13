package resources;

public class Radish extends Resources{
    public double min = 60;
    public double max = 100;

    public Radish() {
        super();
        this.name = "Radish";
        calcBasePrice(min, max);
    }
}
