package storyworlds.service.message;

import storyworlds.exception.InvalidActionException;

/**
 * Created by nvaughan on 9/28/2016.
 */
public interface MessageProcessor {
    public void process(MessageTransport transport) throws InvalidActionException;
}
