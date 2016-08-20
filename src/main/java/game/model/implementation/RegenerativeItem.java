package game.model.implementation;

public class RegenerativeItem {

    private int regenerationInterval;
    
    public RegenerativeItem(int regenerationInterval) {
        this.regenerationInterval = regenerationInterval;
    }

    public int getRegenerationInterval() {
        return regenerationInterval;
    }
}
