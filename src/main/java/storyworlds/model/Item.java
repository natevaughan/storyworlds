package storyworlds.model;

public interface Item {

    public String getName();

    public void setActive(boolean active);

    public boolean isActive();

    public String getDescription();

    public String getUseMessage();

}
