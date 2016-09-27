package storyworlds.gameplay;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import storyworlds.action.Action;
import storyworlds.action.ActionFactory;


public class ActionParserTest {

    @Test
    public void testParser() {
        assertTrue("Parser should successfully parse upper and lowercase QUIT string", Action.QUIT.equals(ActionFactory.parse("qUiT")));
        assertTrue("Parser should successfully parse unknown string as error", Action.ERROR.equals(ActionFactory.parse("foo")));
    }
}

