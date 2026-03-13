package animals;

import model.Game;
import resources.Resources;

public class Sheep extends Animals{
    public double min = 40;
    public double max = 80;
    public String resource = "Sheep";

    public Sheep() {
        super();
        this.name = "Sheep";
        calcBasePrice(min, max);
    }

    @Override
    public void generateResources(Game game) {
        int quantity = game.getPlayer().resourceMap.get(resource).getQuantity();
        game.getPlayer().resourceMap.get(resource).setQuantity(quantity + 1);
    }
}
