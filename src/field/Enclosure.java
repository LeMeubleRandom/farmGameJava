package field;

import animals.Animals;

public class Enclosure extends Field{
    static int enclosureNumber = 0;

    public Enclosure(int newId) {
        super(newId);
        this.setId(enclosureNumber);
        enclosureNumber++;
    }

    static int getEnclosureNumber() {
        return enclosureNumber;
    }

    private int capacity;
    private Animals[] storeAnimals;
}
