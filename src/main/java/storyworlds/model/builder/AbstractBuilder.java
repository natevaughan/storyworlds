package storyworlds.model.builder;

import storyworlds.exception.UncreateableException;

/**
 * Created by nvaughan on 11/5/2016.
 */
public abstract class AbstractBuilder {

    protected void validate(Boolean condition, String message) throws UncreateableException {
        if (condition) {
            throw new UncreateableException(message);
        }
    }
}
