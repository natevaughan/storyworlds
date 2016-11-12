package storyworlds.action;

import java.util.HashSet;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import storyworlds.gameplay.AbstractGameplayTest;
import storyworlds.initial.map.MapFactory;
import storyworlds.model.Direction;
import storyworlds.model.Item;
import storyworlds.model.Location;
import storyworlds.model.Progress;
import storyworlds.model.Storyworld;
import storyworlds.model.implementation.ImmutableLocation;
import storyworlds.model.implementation.StoryworldProgress;
import storyworlds.model.implementation.UsableItem;
import storyworlds.model.implementation.WikiStoryworld;
import storyworlds.service.message.Message;

import static org.junit.Assert.assertTrue;

public class TakeTest extends AbstractGameplayTest {

    private final static String FOO = "foo";

    @Test
    @Ignore // until I mock mongo services
    public void takeItem() throws Exception {
        Item item = new UsableItem(FOO, "", "", user);
        Set<Item> items = new HashSet<>();
        items.add(item);
        Storyworld storyworld = new WikiStoryworld(user);
        Location location = new ImmutableLocation("", storyworld, items, null, user);
        storyworld.setEntry(location);
        Progress progress = new StoryworldProgress(storyworld);
        user.setCurrentProgress(progress);
        user.getCurrentProgress().setLocation(location);
        Actionable a = messageService.process(new Message(user, "take " + FOO));
        assertTrue(Take.class.equals(a.getClass()));
    }

    @Test
    @Ignore
    public void takeThenMoveTest() throws Exception {
        Location location = MapFactory.getStartMap(user);
        user.getCurrentProgress().setLocation(location);
        messageService.process(new Message(user, "take key"));
        messageService.process(new Message(user, "use key"));
        messageService.process(new Message(user, "move down"));
        assertTrue("user should move down", user.getCurrentProgress().getLocation().equals(location.getOutboundLink(Direction.DOWN).getToLocation()));
    }
}
