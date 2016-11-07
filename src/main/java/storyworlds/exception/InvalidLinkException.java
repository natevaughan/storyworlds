package storyworlds.exception;

/**
 * Created by nvaughan on 10/16/2016.
 */
public class InvalidLinkException extends Exception {
    private static final long serialVersionUID = 1080716712557899714L;

    public InvalidLinkException(String message) {
        super(message);
    }
}
