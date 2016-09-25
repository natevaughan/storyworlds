package storyworlds.model.implementation;

import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.Player;

public class DirectionalLink extends AbstractLink implements Link {


    protected final String                  passText;

    public DirectionalLink(String description, String passText, Location endLocation) {
        super(description, endLocation);
        this.passText = passText;
    }

    public String getPassText(Player player) {
        return passText;
    }

    public boolean isPassable(Player player) {
        return true;
    }

}
