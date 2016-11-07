package storyworlds.exception;

/**
 * Created by nvaughan on 11/5/2016.
 */
public class BadRequestException extends Exception {
    private static final long serialVersionUID = 3756428331634164828L;

    public BadRequestException(String message) {
        super(message);
    }
}
