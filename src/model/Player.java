package model;

import animals.*;
import field.Field;
import field.SeedField;
import food.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import resources.*;
import seeds.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Player implements Serializable {
    public String actualChosenSeed = "None";
    public String actualChosenAnimal = "None";
    public String actualChosenFood = "None";
    public int unlockedSeed = 0;
    public int unlockedAnimal = 0;
    public int unlockedFood = 0;
    private String name;
    private int money;
    private int fieldOwned = 0;
    private Field[] fieldsOwned = new SeedField[225];

    public Map<String, Seed> seedMap = new HashMap<>();
    public Map<String, Resources> resourceMap = new HashMap<>();
    public Map<String, Animals> animalMap = new HashMap<>();
    public Map<String, Food> foodMap = new HashMap<>();

    public Player(/*String newName*/) {
        //this.name = newName;
        this.money = 500;
        createSeed();
        createRessources();
        createAnimals();
        createFood();
    }

    public void addField(Field newField) {
        fieldsOwned[fieldOwned] = newField;
        fieldOwned++;
    }

    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void createSeed() {
        seedMap.put("Beet", new BeetSeed(0));
        seedMap.put("Broccoli", new BroccoliSeed(0));
        seedMap.put("Carot", new CarotSeed(0));
        seedMap.put("Corn", new CornSeed(0));
        seedMap.put("Lettuce", new LettuceSeed(0));
        seedMap.put("Peas", new PeasSeed(0));
        seedMap.put("Potatoes", new PotatoesSeed(0));
        seedMap.put("Radish", new RadishSeed(0));
        seedMap.put("Tomatoes", new TomatoesSeed(0));
    }

    public void createRessources() {
        resourceMap.put("Beet", new Beet());
        resourceMap.put("Broccoli", new Broccoli());
        resourceMap.put("Carot", new Carot());
        resourceMap.put("Corn", new Corn());
        resourceMap.put("Hen", new Egg());
        resourceMap.put("Lettuce", new Lettuce());
        resourceMap.put("Pig", new Meat());
        resourceMap.put("Cow", new Milk());
        resourceMap.put("Peas", new Peas());
        resourceMap.put("Potatoes", new Potatoes());
        resourceMap.put("Radish", new Radish());
        resourceMap.put("Tomatoes", new Tomatoes());
        resourceMap.put("Sheep", new Wool());
    }

    public void createAnimals() {
        animalMap.put("Hen", new Hen());
        animalMap.put("Pig", new Pig());
        animalMap.put("Cow", new Cow());
        animalMap.put("Sheep", new Sheep());
    }

    public void createFood() {
        foodMap.put("Hen", new HenFood(0));
        foodMap.put("Pig", new PigFood(0));
        foodMap.put("Cow", new CowFood(0));
        foodMap.put("Sheep", new SheepFood(0));
    }

    public int getMapQuantity(String menuName, String menuChoice) {
        return switch (menuName) {
            case "Seeds" -> seedMap.get(menuChoice).getQuantity();
            case "Resources" -> resourceMap.get(menuChoice).getQuantity();
            case "Animals" -> animalMap.get(menuChoice).getQuantity();
            case "Food" -> foodMap.get(menuChoice).getQuantity();
            default -> 0;
        };
    }

    public void setMapQuantity(String menuName, String menuChoice, int quantity, int numberToAdd) {
        switch (menuName) {
            case "Seeds":
                seedMap.get(menuChoice).setQuantity(quantity + numberToAdd);
                break;
            case "Resources":
                resourceMap.get(menuChoice).setQuantity(quantity + numberToAdd);
                break;
            case "Animals":
                animalMap.get(menuChoice).setQuantity(quantity + numberToAdd);
                break;
            case "Food":
                foodMap.get(menuChoice).setQuantity(quantity + numberToAdd);
        }
    }

    public int getMapPrice(String menuName, String menuChoice) {
        return switch (menuName) {
            case "Seeds" -> seedMap.get(menuChoice).getPrice();
            case "Resources" -> resourceMap.get(menuChoice).getPrice();
            case "Animals" -> animalMap.get(menuChoice).getPrice();
            case "Food" -> foodMap.get(menuChoice).getPrice();
            default -> 0;
        };
    }
}
