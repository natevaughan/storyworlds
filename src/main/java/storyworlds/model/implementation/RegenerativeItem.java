package storyworlds.model.implementation;

public class RegenerativeItem extends UsableItem {

    private int regenerationInterval;

    public RegenerativeItem(String name, String properties, int regenerationInterval) {
        super(name, properties);
        this.regenerationInterval = regenerationInterval;
    }

    public int getRegenerationInterval() {
        return regenerationInterval;
    }
}
