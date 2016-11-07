package storyworlds.model;

import storyworlds.exception.UncreateableException;

/**
 * Created by nvaughan on 11/5/2016.
 */
public interface Buildable<T> {
    public T build() throws UncreateableException;
}
