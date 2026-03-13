package resources;

public class Egg extends Resources{
    public double min = 60;
    public double max = 100;

    public Egg() {
        super();
        this.name = "Egg";
        calcBasePrice(min, max);
    }
}
