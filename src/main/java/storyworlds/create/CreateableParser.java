package storyworlds.create;

import storyworlds.exception.UncreateableException;

public class CreateableParser {

    public static Createable parse(String input) throws UncreateableException {

        for (Createable createables : Createable.values()) {
            if (createables.toString().equalsIgnoreCase(input)) {
                return createables;
            }
        }

        throw new UncreateableException("Unrecognized createable: " + input);
    }

}
