package game.exception;

public class IncompleteLocationException extends Exception {

    public IncompleteLocationException(String message, Location location) {
        super(message + location.getText());
    }
}
