package storyworlds.exception;

/**
 * Created by nvaughan on 11/5/2016.
 */
public class UnrecognizedInputException extends Exception {
    private static final long serialVersionUID = -6146132614067873289L;

    public UnrecognizedInputException(String message) {
        super(message);
    }
}
