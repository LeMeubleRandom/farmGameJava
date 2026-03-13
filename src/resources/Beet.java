package resources;

public class Beet extends Resources{
    public double min = 60;
    public double max = 100;

    public Beet() {
        super();
        this.name = "Beet";
        calcBasePrice(min, max);
    }
}
