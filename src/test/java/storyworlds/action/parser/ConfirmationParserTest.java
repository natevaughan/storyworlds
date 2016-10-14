package storyworlds.action.parser;

import org.junit.Test;
import storyworlds.model.enumeration.Confirmation;

import static org.junit.Assert.assertTrue;

/**
 * Created by nvaughan on 10/13/2016.
 */
public class ConfirmationParserTest {

    ConfirmationParser parser = new ConfirmationParser();

    @Test
    public void testParseYes() {
        assertTrue(Confirmation.YES.equals(parser.parse("yes")));
        assertTrue(Confirmation.YES.equals(parser.parse("YeS")));
        assertTrue(Confirmation.YES.equals(parser.parse("y")));
        assertTrue(Confirmation.YES.equals(parser.parse("y ")));
    }

    @Test
    public void testParseNo() {
        assertTrue(Confirmation.NO.equals(parser.parse(" n ")));
        assertTrue(Confirmation.NO.equals(parser.parse("N")));
        assertTrue(Confirmation.NO.equals(parser.parse("No")));
        assertTrue(Confirmation.NO.equals(parser.parse("nO")));
    }

    @Test
    public void testParseError() {
        assertTrue(Confirmation.ERROR.equals(parser.parse(" ")));
        assertTrue(Confirmation.ERROR.equals(parser.parse("")));
        assertTrue(Confirmation.ERROR.equals(parser.parse("foo")));
        assertTrue(Confirmation.ERROR.equals(parser.parse("y es")));
    }
}
