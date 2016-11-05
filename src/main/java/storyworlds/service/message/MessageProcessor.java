package storyworlds.service.message;

import storyworlds.exception.InvalidDirectionException;
import storyworlds.exception.InvalidLinkException;
import storyworlds.exception.UnrecognizedInputException;

/**
 * Created by nvaughan on 9/28/2016.
 */
public interface MessageProcessor {
    public void process(MessageTransport transport) throws InvalidLinkException, UnrecognizedInputException, InvalidDirectionException;
}
