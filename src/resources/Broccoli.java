package resources;

public class Broccoli extends Resources{
    public double min = 60;
    public double max = 100;

    public Broccoli() {
        super();
        this.name = "Broccoli";
        calcBasePrice(min, max);
    }
}
