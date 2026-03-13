package animals;

import model.Game;
import resources.Resources;

public class Cow extends Animals{
    public double min = 60;
    public double max = 100;
    public String resource = "Cow";

    public Cow() {
        super();
        this.name = "Cow";
        calcBasePrice(min, max);
    }

    @Override
    public void generateResources(Game game) {
        int quantity = game.getPlayer().resourceMap.get(resource).getQuantity();
        game.getPlayer().resourceMap.get(resource).setQuantity(quantity + 1);
    }
}
