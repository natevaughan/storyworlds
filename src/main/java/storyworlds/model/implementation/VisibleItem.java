package storyworlds.model.implementation;

import storyworlds.model.Item;
import storyworlds.model.Player;

/**
 * Created by nvaughan on 9/10/2016.
 */
public class VisibleItem implements Item {

    private String name;
    private String properties;

    public VisibleItem(String name, String properties) {
        this.name = name;
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public String getProperties() {
        return properties;
    }

    public boolean isVisible(Player player) {
        return true;
    }
}
