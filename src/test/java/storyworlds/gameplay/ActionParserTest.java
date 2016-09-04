package storyworlds.gameplay;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import storyworlds.action.Action;

public class ActionParserTest {

    private ActionParser actionParser = new ActionParser();
    
    @Test
    public void testParser() {
        assertTrue("Parser should successfully parse upper and lowercase QUIT string", Action.QUIT.getActionable().equals(actionParser.parse("qUiT")));
        assertTrue("Parser should successfully parse upper and lowercase MAP string", Action.MAP.getActionable().equals(actionParser.parse("maP")));
        assertTrue("Parser should successfully parse upper and lowercase MOVE string", Action.MOVE.getActionable().equals(actionParser.parse("MOve")));
        assertTrue("Parser should successfully parse upper and lowercase TAKE string", Action.TAKE.getActionable().equals(actionParser.parse("TAKE")));
        assertTrue("Parser should successfully parse upper and lowercase USE string", Action.USE.getActionable().equals(actionParser.parse("usE")));
    }
}

