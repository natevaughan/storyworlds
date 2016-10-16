package storyworlds.create;

import org.junit.Ignore;
import org.junit.Test;
import storyworlds.action.Actionable;
import storyworlds.action.Edit;
import storyworlds.exception.BadLinkException;
import storyworlds.gameplay.AbstractGameplayTest;
import storyworlds.service.message.Message;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Ignore
public class LocationEditorTest extends AbstractGameplayTest {

    @Test
    public void editExistingLocation() throws BadLinkException {
        Actionable a = messageService.process(new Message(user, "edit location"));
        assertTrue("Actionable should be Edit", Edit.class.equals(a.getClass()));
    }


    @Test
    public void editExistingLink() throws BadLinkException {
        Actionable a = messageService.process(new Message(user, "edit link"));
        assertTrue("Actionable should be Edit", Edit.class.equals(a.getClass()));
        assertTrue("Edit should succeed", a.isSuccessful());
    }


    @Test
    public void badEdit() throws BadLinkException {
        Actionable a = messageService.process(new Message(user, "edit link foo"));
        assertTrue("Actionable should be Edit", Edit.class.equals(a.getClass()));
        assertFalse("Edit should fail", a.isSuccessful());
    }

}
