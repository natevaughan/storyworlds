package storyworlds.model.builder;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Buildable;
import storyworlds.model.Item;
import storyworlds.model.Location;
import storyworlds.model.Player;
import storyworlds.model.Storyworld;

import java.util.Collection;

/**
 * Created by nvaughan on 10/22/2016.
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
public interface LocationBuilder extends Buildable<Location> {

    LocationBuilder setDescription(String description);

    LocationBuilder setStoryworld(Storyworld storyworld);

    LocationBuilder add(Item item);

    LocationBuilder addAll(Collection<Item> items);

    LocationBuilder setPreviousLocation(Location previousLocation);

    LocationBuilder setCreator(Player creator);
}
