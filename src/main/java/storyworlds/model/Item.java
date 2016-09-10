package storyworlds.model;

public interface Item {

    public String getName();

    public String getProperties();

    public boolean isVisible(Player player);
}
