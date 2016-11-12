package storyworlds.model.builder;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import storyworlds.model.Buildable;
import storyworlds.model.Storyworld;
import storyworlds.model.implementation.IdentifiedPlayer;

/**
 * Created by nvaughan on 11/12/2016.
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
public interface StoryworldBuilder extends Buildable<Storyworld> {
    public StoryworldBuilder setCreator(IdentifiedPlayer player);
    public LocationBuilder getEntryBuilder();
}
