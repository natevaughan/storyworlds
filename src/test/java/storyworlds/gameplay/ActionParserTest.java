package storyworlds.gameplay;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import storyworlds.action.Action;
import storyworlds.action.parser.ActionParser;

public class ActionParserTest {

    private ActionParser actionParser = new ActionParser();
    
    @Test
    public void testParser() {
        assertTrue("Parser should successfully parse upper and lowercase QUIT string", Action.QUIT.equals(actionParser.parse("qUiT")));
        assertTrue("Parser should successfully parse unknown string as error", Action.ERROR.equals(actionParser.parse("foo")));
    }
}

