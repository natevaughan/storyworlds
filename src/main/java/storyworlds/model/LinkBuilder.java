package storyworlds.model;

import storyworlds.exception.UncreateableException;

/**
 * Created by nvaughan on 10/22/2016.
 */
public interface LinkBuilder {

    public LinkBuilder setPassText(String passText);
    public LinkBuilder setDescription(String description);
    public LinkBuilder setToLocation(Location toLocation);
    public LinkBuilder setCreator(Player creator);
    public Link build() throws UncreateableException;
}
