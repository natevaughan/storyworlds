package storyworlds.model;

public interface Item extends Persistable {

    public String getName();

    public String getDescription();

    public String getUseText();

    public Player getCreator();

}
