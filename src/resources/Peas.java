package resources;

public class Peas extends Resources{
    public double min = 60;
    public double max = 100;

    public Peas() {
        super();
        this.name = "Peas";
        calcBasePrice(min, max);
    }
}
