package storyworlds.model;

import storyworlds.exception.UncreateableException;

/**
 * Created by nvaughan on 10/22/2016.
 */
public interface ItemBuilder {
    Item build() throws UncreateableException;
}
