package storyworlds.action.parser;

import org.apache.commons.lang3.StringUtils;
import storyworlds.exception.InvalidColorException;

/**
 * Created by nvaughan on 11/12/2016.
 */
public class HexColor {
    public static String validate(String input) throws InvalidColorException {
        if (StringUtils.isEmpty(input)) {
            throw new InvalidColorException("Invalid color: empty");
        }
        input = input.toUpperCase();
        if (!(input.length() == 7 || input.length() == 4) || input.charAt(0) != '#') {
            throw new InvalidColorException("Invalid color: only colors following the form #--- or #------- are allowed.");
        }
        if (!input.substring(1).matches("[0-9A-F]+")) {
            throw new InvalidColorException("Invalid color: only hex characters 0 through 9 and a through f are allowed.");
        }

        return input;
    }
}
