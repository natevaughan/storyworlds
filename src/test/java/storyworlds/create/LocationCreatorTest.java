package storyworlds.create;

import org.junit.Ignore;
import org.junit.Test;
import storyworlds.action.Actionable;
import storyworlds.action.Create;
import storyworlds.gameplay.AbstractMapGameplayTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@Ignore
public class LocationCreatorTest extends AbstractMapGameplayTest {

    @Test
    public void createLocation() {
        Actionable a = executor.execute("create location west");
        assertTrue("Actionable should be Createables", Create.class.equals(a.getClass()));
        assertTrue("Createables should be successful", a.isSuccessful());
    }

    @Test
    public void overwriteExistingLocation() {
        setup();
        Actionable a = executor.execute("create location north");
        assertFalse("Createables should fail", a.isSuccessful());

    }

    @Test
    public void createBadLocation() {
        Actionable a = executor.execute("create location foo");
        assertTrue("Actionable should be Createables", Create.class.equals(a.getClass()));
        assertFalse("Createables should be successful", a.isSuccessful());
    }
}

