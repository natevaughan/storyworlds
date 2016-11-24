package storyworlds.create;

import storyworlds.exception.UncreateableException;

public class CreateableParser {

    public static CreateableType parse(String input) throws UncreateableException {

        for (CreateableType createables : CreateableType.values()) {
            if (createables.toString().equalsIgnoreCase(input)) {
                return createables;
            }
        }

        throw new UncreateableException("Unrecognized createable: " + input);
    }

}
