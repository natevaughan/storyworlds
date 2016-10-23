package storyworlds.action;

import org.junit.Ignore;
import org.junit.Test;
import storyworlds.exception.BadLinkException;
import storyworlds.gameplay.AbstractGameplayTest;
import storyworlds.initial.map.MapFactory;
import storyworlds.model.Item;
import storyworlds.model.Location;
import storyworlds.model.enumeration.Direction;
import storyworlds.model.implementation.ImmutableLocation;
import storyworlds.model.implementation.UsableItem;
import storyworlds.model.implementation.WikiStoryworld;
import storyworlds.service.message.Message;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class TakeTest extends AbstractGameplayTest {

    @Test
    public void takeItem() throws BadLinkException {
        Item item = new UsableItem("bar", "", "", user);
        Set<Item> items = new HashSet<>();
        items.add(item);
        Location location = new ImmutableLocation("", new WikiStoryworld(), items, null, user);
        user.setLocation(location);
        Actionable a = messageService.process(new Message(user, "take foo"));
        assertTrue(Take.class.equals(a.getClass()));
    }

    @Test
    @Ignore
    public void takeThenMoveTest() throws BadLinkException {
        Location location = MapFactory.getStartMap(user);
        user.setLocation(location);
        messageService.process(new Message(user, "take key"));
        messageService.process(new Message(user, "use key"));
        messageService.process(new Message(user, "move down"));
        assertTrue("user should move down", user.getLocation().equals(location.getOutboundLink(Direction.DOWN).getToLocation()));
    }
}
