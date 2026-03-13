package resources;

public class Lettuce extends Resources{
    public double min = 60;
    public double max = 100;

    public Lettuce() {
        super();
        this.name = "Lettuce";
        calcBasePrice(min, max);
    }
}
