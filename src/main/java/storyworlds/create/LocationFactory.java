package storyworlds.create;


import storyworlds.model.Location;
import storyworlds.model.Player;
import storyworlds.model.implementation.ImmutableLocation;

public class LocationFactory implements Creatable {

    private Player player;

    public LocationFactory(Player player) {
        this.player = player;
    }

    public Location createLocation() {
        return null;
    }
}

