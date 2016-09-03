package storyworlds.gameplay;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import storyworlds.model.Action;

public class ActionParserTest {

    private ActionParser actionParser = new ActionParser();
    
    @Test
    public void testParser() {
        assertTrue("Parser should successfully parse upper and lowercase QUIT string", Action.QUIT.equals(actionParser.parse("qUiT")));
        assertTrue("Parser should successfully parse upper and lowercase MAP string", Action.MAP.equals(actionParser.parse("maP")));
        assertTrue("Parser should successfully parse upper and lowercase MOVE string", Action.MOVE.equals(actionParser.parse("MOve")));
        assertTrue("Parser should successfully parse upper and lowercase TAKE string", Action.TAKE.equals(actionParser.parse("TAKE")));
        assertTrue("Parser should successfully parse upper and lowercase USE string", Action.USE.equals(actionParser.parse("usE")));
    }
}

