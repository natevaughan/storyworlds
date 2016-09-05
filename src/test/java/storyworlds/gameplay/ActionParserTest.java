package storyworlds.gameplay;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import storyworlds.action.Action;
import storyworlds.action.ActionFactory;
import storyworlds.action.parser.ActionParser;

public class ActionParserTest {

    private ActionParser actionParser = new ActionParser();
    
    @Test
    public void testParser() {
        assertTrue("Parser should successfully parse upper and lowercase QUIT string", ActionFactory.get(Action.QUIT).getClass().equals(actionParser.parse("qUiT").getClass()));
        assertFalse("Parser should successfully parse unknown string as error", ActionFactory.get(Action.QUIT).getClass().equals(actionParser.parse("foo").getClass()));
    }
}

