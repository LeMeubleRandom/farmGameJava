package animals;

import model.Game;

public class Hen extends Animals{
    public double min = 20;
    public double max = 60;
    public String resource = "Hen";

    public Hen() {
        super();
        this.name = "Hen";
        calcBasePrice(min, max);
    }
    @Override
    public void generateResources(Game game) {
        int quantity = game.getPlayer().resourceMap.get(resource).getQuantity();
        game.getPlayer().resourceMap.get(resource).setQuantity(quantity + 1);
    }
}
