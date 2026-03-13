package animals;

import model.Game;

public class Pig extends Animals{
    public double min = 50;
    public double max = 70;
    public String resource = "Pig";

    public Pig() {
        super();
        this.name = "Pig";
        calcBasePrice(min, max);
    }
    public void generateResources(Game game) {
        int quantity = game.getPlayer().resourceMap.get(resource).getQuantity();
        game.getPlayer().resourceMap.get(resource).setQuantity(quantity + 1);
    }
}
