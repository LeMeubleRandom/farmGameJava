package resources;

public class Corn extends Resources {
    public double min = 60;
    public double max = 100;

    public Corn() {
        super();
        this.name = "Corn";
        calcBasePrice(min, max);
    }
}
