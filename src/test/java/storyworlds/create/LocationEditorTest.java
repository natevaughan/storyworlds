package storyworlds.create;

import org.junit.Ignore;
import org.junit.Test;
import storyworlds.action.Actionable;
import storyworlds.action.Edit;
import storyworlds.gameplay.AbstractGameplayTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Ignore
public class LocationEditorTest extends AbstractGameplayTest {

    @Test
    public void editExistingLocation() {
        Actionable a = executor.execute("edit location");
        assertTrue("Actionable should be Edit", Edit.class.equals(a.getClass()));
    }


    @Test
    public void editExistingLink() {
        Actionable a = executor.execute("edit location");
        assertTrue("Actionable should be Edit", Edit.class.equals(a.getClass()));
        assertTrue("Edit should succeed", a.isSuccessful());
    }


    @Test
    public void badEdit() {
        Actionable a = executor.execute("edit link foo");
        assertTrue("Actionable should be Edit", Edit.class.equals(a.getClass()));
        assertFalse("Edit should fail", a.isSuccessful());
    }

}
