package game.gameplay;

import static org.junit.Assert.*;
import org.junit.Test;

import storyworlds.gameplay.ActionParser;
import storyworlds.model.*;

public class ActionParserTest {

    @Test
    public void testParser() {
        assertTrue("Parser should successfully parse upper and lowercase QUIT string", Action.QUIT.equals(ActionParser.parse("qUiT")));
        assertTrue("Parser should successfully parse upper and lowercase MAP string", Action.MAP.equals(ActionParser.parse("maP")));
        assertTrue("Parser should successfully parse upper and lowercase MOVE string", Action.MOVE.equals(ActionParser.parse("MOve")));
        assertTrue("Parser should successfully parse upper and lowercase TAKE string", Action.TAKE.equals(ActionParser.parse("TAKE")));
        assertTrue("Parser should successfully parse upper and lowercase USE string", Action.USE.equals(ActionParser.parse("usE")));
    }
}

