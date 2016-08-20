package game.model;

import java.util.Collection;

public interface Location { 

    public String getText();

    public Collection<Player> listPlayers();

    public Collection<Item> listItems();
    
    public String getMap();

    public Collection<Link> listLinks();

    public Link getLink(Direction direction);

    public void setLink(Direction direction, Link link);
}
