package storyworlds.action;

import org.junit.Test;
import storyworlds.gameplay.AbstractGameplayTest;
import storyworlds.initial.map.MapFactory;
import storyworlds.model.Item;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.enumeration.Direction;
import storyworlds.model.implementation.ImmutableLocation;
import storyworlds.model.implementation.UsableItem;
import storyworlds.service.message.Message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;

public class TakeTest extends AbstractGameplayTest {

    @Test
    public void takeItem() {
        Item item = new UsableItem("bar", "", "");
        Collection<Item> items = new ArrayList<>();
        items.add(item);
        Location location = new ImmutableLocation("", null, items);
        user.setLocation(location);
        Actionable a = messageService.process(new Message(user, "take foo"));
        assertTrue(Take.class.equals(a.getClass()));
    }

    @Test
    public void takeThenMoveTest() {
        Location location = MapFactory.getStartMap();
        user.setLocation(location);
        messageService.process(new Message(user, "take key"));
        messageService.process(new Message(user, "use key"));
        messageService.process(new Message(user, "move down"));
        assertTrue("user should move down", user.getLocation().equals(location.getOutboundLink(Direction.DOWN).getToLocation()));
    }
}
