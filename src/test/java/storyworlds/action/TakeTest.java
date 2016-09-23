package storyworlds.action;

import org.junit.Test;
import storyworlds.gameplay.AbstractGameplayTest;
import storyworlds.model.Direction;
import storyworlds.model.Item;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.implementation.ImmutableLocation;
import storyworlds.model.implementation.UsableItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

/**
 * Created by nvaughan on 9/23/2016.
 */
public class TakeTest extends AbstractGameplayTest {

    @Test
    public void takeItem() {
        Item item = new UsableItem("bar", "", "");
        Collection<Item> items = new ArrayList<>();
        items.add(item);
        Location location = new ImmutableLocation("", items, new HashMap<Direction, Link>());
        user.setLocation(location);
        Action action = executor.execute("take foo");
        assertTrue(Action.TAKE.equals(action));
    }

}
