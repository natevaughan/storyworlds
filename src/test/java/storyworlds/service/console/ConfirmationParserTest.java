package storyworlds.service.console;

import org.junit.Test;
import storyworlds.service.console.ConfirmationParser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConfirmationParserTest {

    ConfirmationParser parser = new ConfirmationParser();

    @Test
    public void testParseYes() {
        assertTrue(parser.parse("yes"));
        assertTrue(parser.parse("YeS"));
        assertTrue(parser.parse("y"));
        assertTrue(parser.parse("y "));
        assertFalse(parser.parse(" n "));
        assertFalse(parser.parse("foo"));
    }
}
