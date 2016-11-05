package storyworlds.model.implementation;

import storyworlds.exception.UncreateableException;
import storyworlds.model.LinkBuilder;
import storyworlds.model.Location;
import storyworlds.model.LocationBuilder;
import storyworlds.model.Player;

public class DirectionalLink extends AbstractLink {

    protected final String                  passText;

    public DirectionalLink(String description, Location toLocation, String passText, Player creator) {
        super(description, toLocation, creator);
        this.passText = passText;
    }

    public String getPassText(Player player) {
        return passText;
    }

    public boolean isPassable(Player player) {
        return true;
    }
}
