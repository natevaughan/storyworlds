package storyworlds.model.implementation; 

import java.util.Collection;
import java.util.HashSet;

import storyworlds.model.*;

public class User implements Player {

    protected final String name;
    protected Collection<Item> items;
    protected Direction facingDirection;
    protected Location location;

    public User(String name) {
        this(name, Direction.NORTH);
    }
    
    public User(String name, Direction direction) {
        this.name = name;
        this.facingDirection = direction;
        this.items = new HashSet<Item>();
    }

    public String getName() {
        return name;
    }


    public Collection<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }
    
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Direction getDirection() {
        return facingDirection;
    }

    public void setDirection(Direction direction) {
        this.facingDirection = direction;
    }
}	

