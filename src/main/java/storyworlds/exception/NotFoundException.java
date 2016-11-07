package storyworlds.exception;

/**
 * Created by nvaughan on 11/5/2016.
 */
public class NotFoundException extends Exception {
    private static final long serialVersionUID = 8195495110557751279L;

    public NotFoundException(String message) {
        super(message);
    }
}
