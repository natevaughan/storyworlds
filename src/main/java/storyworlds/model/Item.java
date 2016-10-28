package storyworlds.model;

public interface Item extends Comparable<Item> {

    public String getId();

    public String getName();

    public String getDescription();

    public String getUseText();

    public Player getCreator();

}
