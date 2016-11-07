package storyworlds.exception;

/**
 * Created by nvaughan on 11/5/2016.
 */
public class UnauthorizedException extends Exception {
    private static final long serialVersionUID = 3419382496316988205L;

    public UnauthorizedException(String message) {
        super(message);
    }
}
