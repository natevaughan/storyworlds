package storyworlds.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import storyworlds.exception.UncreateableException;

/**
 * Created by nvaughan on 10/22/2016.
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
public interface LinkBuilder {

    LinkBuilder setPassText(String passText);
    LinkBuilder setDescription(String description);
    LinkBuilder setToLocation(Location toLocation);
    LinkBuilder setCreator(Player creator);
    Link build() throws UncreateableException;
}
