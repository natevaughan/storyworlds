package storyworlds.action.parser;

import org.junit.Test;
import storyworlds.exception.InvalidColorException;

import static org.junit.Assert.assertTrue;

/**
 * Created by nvaughan on 11/12/2016.
 */
public class HexColorTest {

    private final static String VALID_COLOR = "#123abc";
    private final static String VALID_COLOR_UPPERCASE = "#123ABC";
    private final static String VALID_COLOR_2 = "#123456";

    @Test
    public void validateTest() throws InvalidColorException {
        assertTrue("Hex color " + VALID_COLOR + "should pass validation ", HexColor.validate(VALID_COLOR).equals(VALID_COLOR_UPPERCASE));
        assertTrue("Hex color " + VALID_COLOR_2 + "should pass validation ", HexColor.validate(VALID_COLOR_2).equals(
                VALID_COLOR_2));
    }

    @Test(expected = InvalidColorException.class)
    public void validateTest2() throws InvalidColorException {
        HexColor.validate("#12");
    }

    @Test(expected = InvalidColorException.class)
    public void validateTest3() throws InvalidColorException {
        HexColor.validate("foo");
    }
}
