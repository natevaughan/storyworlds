package storyworlds.service.console;

public class ConfirmationParser {
    private enum Confirmation {
        YES, Y, SURE, OK
    }
    public static boolean parse(String input) {
        input = input.trim();
        for (Confirmation confirmation : Confirmation.values()) {
            if (confirmation.toString().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}
