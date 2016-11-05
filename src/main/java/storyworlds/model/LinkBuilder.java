package storyworlds.model;

import storyworlds.exception.UncreateableException;

/**
 * Created by nvaughan on 10/22/2016.
 */
public interface LinkBuilder {

    LinkBuilder setPassText(String passText);
    LinkBuilder setDescription(String description);
    LinkBuilder setToLocation(Location toLocation);
    LinkBuilder setCreator(Player creator);
    Link build() throws UncreateableException;
}
