package storyworlds.service.console;

public class ConfirmationParser {
    public static Confirmation parse(String input) {
        input = input.trim();
        for (Confirmation confirmation : Confirmation.values()) {
            if (confirmation.toString().equalsIgnoreCase(input) || confirmation.toString().substring(0,1).equalsIgnoreCase(input)) {
                return confirmation;
            }
        }
        return Confirmation.ERROR;
    }
}
