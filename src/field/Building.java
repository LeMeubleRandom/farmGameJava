package field;

public class Building extends Field{
    static int buildingNumber = 0;

    public Building(int newId) {
        super(newId);
        this.setId(buildingNumber);
        buildingNumber++;
    }

    public int rentability() {
        return 50 * this.getLevel();
    }

    static int getBuildingNumber() {
        return buildingNumber;
    }
}
