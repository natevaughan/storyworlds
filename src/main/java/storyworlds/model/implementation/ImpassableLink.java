package storyworlds.model.implementation;

import storyworlds.constants.GameTextConstants;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.Player;

public class ImpassableLink implements Link, GameTextConstants {
    
    private final String text;
    
    public ImpassableLink(String text) {
        this.text = text;
    }    
    public ImpassableLink() {
        this(BLANK_LINK_TEXT);
    }
    
    public boolean isPassable(Player player) {
        return false;
    }

    public String getText(Player player) {
        return text;
    }

    public Location getLinkedLocation(Location startLocation) {
        return startLocation;
    }

}
