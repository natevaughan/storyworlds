package storyworlds.model.builder;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import storyworlds.model.Buildable;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.Player;

/**
 * Created by nvaughan on 10/22/2016.
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
public interface LinkBuilder extends Buildable<Link> {

    LinkBuilder setPassText(String passText);
    LinkBuilder setDescription(String description);
    LinkBuilder setToLocation(Location toLocation);
    LinkBuilder setToLocationBuilder(LocationBuilder locationBuilder);
    LocationBuilder getToLocationBuilder();
    LinkBuilder setToLocationId(String id);
    String getToLocationId();
    LinkBuilder setCreator(Player creator);
}
