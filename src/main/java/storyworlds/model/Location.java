package storyworlds.model;

import java.util.Collection;
import java.util.Map;

public interface Location { 

    public String getText();

    public Collection<Player> listPlayers();

    public Collection<Item> listItems();
    
    public String getMap();

    public Map<Direction, Link> getLinks();

    public Link getLink(Direction direction);

    public void setLink(Direction direction, Link link);
}
