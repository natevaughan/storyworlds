package storyworlds.creator;

import org.junit.Before;
import org.junit.Test;
import storyworlds.action.Actionable;
import storyworlds.action.Create;
import storyworlds.action.Edit;
import storyworlds.factory.MapFactory;
import storyworlds.gameplay.AbstractMapGameplayTest;
import storyworlds.model.Location;
import storyworlds.model.Player;
import storyworlds.model.implementation.User;

import static org.junit.Assert.assertTrue;

public class LocationCreatorTest extends AbstractMapGameplayTest {

    @Test
    public void editExistingLocation() {
        Actionable a = executor.execute("edit location");
        assertTrue("Actionable should be Edit", Edit.class.equals(a.getClass()));
    }

    @Test
    public void createLocation() {
        Actionable a = executor.execute("create location");
        assertTrue("Actionable should be Create", Create.class.equals(a.getClass()));

    }

    @Test
    public void createLink() {

    }
}

