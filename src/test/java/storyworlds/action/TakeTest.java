package storyworlds.action;

import org.junit.Test;
import storyworlds.factory.MapFactory;
import storyworlds.gameplay.AbstractGameplayTest;
import storyworlds.model.*;
import storyworlds.model.implementation.ImmutableLocation;
import storyworlds.model.implementation.UsableItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class TakeTest extends AbstractGameplayTest {

    @Test
    public void takeItem() {
        Item item = new UsableItem("bar", "", "");
        Collection<Item> items = new ArrayList<>();
        items.add(item);
        Location location = new ImmutableLocation("", items, new HashMap<Direction, Link>());
        user.setLocation(location);
        Actionable actionable = executor.execute("take foo");
        assertTrue(Take.class.equals(actionable.getClass()));
    }

    @Test
    public void takeThenMoveTest() {
        Location location = MapFactory.getStartMap();
        user.setLocation(location);
        executor.execute("take key");
        executor.execute("use key");
        executor.execute("move down");
        assertTrue("user should move down", user.getLocation().equals(location.getLink(Direction.DOWN).getLinkedLocation(location)));
    }
}
