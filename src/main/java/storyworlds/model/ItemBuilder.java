package storyworlds.model;

import storyworlds.exception.UncreateableException;
import storyworlds.model.implementation.UsableItem;

/**
 * Created by nvaughan on 10/22/2016.
 */
public interface ItemBuilder {
    public Item build() throws UncreateableException;
}
