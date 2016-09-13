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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsableItem that = (UsableItem) o;

        if (active != that.active) return false;
        if (!name.equals(that.name)) return false;
        return properties.equals(that.properties);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + properties.hashCode();
        result = 31 * result + (active ? 1 : 0);
        return result;
    }
}
