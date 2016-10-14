package storyworlds.action.parser;

import storyworlds.model.enumeration.Confirmation;

public class ConfirmationParser {
    public Confirmation parse(String input) {
        input = input.trim();
        for (Confirmation confirmation : Confirmation.values()) {
            if (confirmation.toString().equalsIgnoreCase(input) || confirmation.toString().substring(0,1).equalsIgnoreCase(input)) {
                return confirmation;
            }
        }
        return Confirmation.ERROR;
    }
}
