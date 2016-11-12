package storyworlds.action.parser;

import org.junit.Test;
import storyworlds.action.Action;
import storyworlds.action.ActionFactory;
import storyworlds.exception.UnrecognizedInputException;

import static org.junit.Assert.assertTrue;


public class ActionParserTest {

    @Test
    public void testParser() throws UnrecognizedInputException {
        assertTrue("Parser should successfully validate upper and lowercase move string", Action.MOVE.equals(ActionFactory.parse("MOVe")));
        assertTrue("Parser should successfully validate upper and lowercase move string", Action.MOVE.equals(ActionFactory.parse(" movE ")));
    }

    @Test(expected = UnrecognizedInputException.class)
    public void badInput() throws UnrecognizedInputException {
        ActionFactory.parse("foo");
    }
}

