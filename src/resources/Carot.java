package resources;

public class Carot extends Resources {
    public double min = 60;
    public double max = 100;

    public Carot() {
        super();
        this.name = "Carot";
        calcBasePrice(min, max);
    }
}
