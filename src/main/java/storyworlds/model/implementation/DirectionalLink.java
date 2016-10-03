package storyworlds.model.implementation;

import storyworlds.model.Direction;
import storyworlds.model.Location;
import storyworlds.model.Player;

public class DirectionalLink extends AbstractLink {

    protected final String                  passText;

    public DirectionalLink(String description, Location toLocation, Location fromLocation, Direction fromDirection, String passText) {
        super(description, toLocation, fromLocation, fromDirection);
        this.passText = passText;
    }

    public String getPassText(Player player) {
        return passText;
    }

    public boolean isPassable(Player player) {
        return true;
    }

}
