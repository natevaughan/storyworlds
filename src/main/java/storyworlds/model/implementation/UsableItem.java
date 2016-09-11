package storyworlds.model.implementation;

import storyworlds.model.Item;
import storyworlds.model.Player;

/**
 * Created by nvaughan on 9/10/2016.
 */
public class UsableItem implements Item {

    private String name;
    private String properties;
    private boolean active;

    public UsableItem(String name, String properties) {
        this.name = name;
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public String getProperties() {
        return properties;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
