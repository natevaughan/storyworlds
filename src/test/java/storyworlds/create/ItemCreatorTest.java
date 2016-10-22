package storyworlds.create;

import org.junit.Ignore;
import org.junit.Test;
import storyworlds.action.Actionable;
import storyworlds.action.Create;
import storyworlds.exception.UncreateableException;
import storyworlds.gameplay.AbstractMapGameplayTest;
import storyworlds.model.Item;
import storyworlds.model.implementation.UsableItem;
import storyworlds.service.ItemService;
import storyworlds.service.message.Message;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by nvaughan on 10/7/2016.
 */
public class ItemCreatorTest extends AbstractMapGameplayTest {

    private final String ITEM_NAME = "arbitrary.item.name";
    private final String ITEM_DESCRIPTION = "arbitrary.item.description";
    private final String ITEM_USE_TEXT = "arbitrary.item.use.text";

    @Test
    @Ignore // investigate
    public void createItemTest() throws Exception {
        Actionable a = messageService.process(new Message(user, "build item"));
        assertTrue(a.isSuccessful());
        if (a instanceof Create) {
            ItemService itemService = new ItemService();
            UsableItem.Builder b = UsableItem.Builder.newInstance();
            b.setName(ITEM_NAME);
            b.setDescription(ITEM_DESCRIPTION);
            b.setUseText(ITEM_USE_TEXT);
            Item item = itemService.create(b);
            assertNotNull(item);
        } else {
            assertTrue("Actionable not instance of Create.", false);
        }
    }

    @Test(expected = UncreateableException.class)
    public void twoWordNameTest() throws Exception {
        Actionable a = messageService.process(new Message(user, "build item"));

        if (a instanceof Create) {
            ItemService itemService = new ItemService();
            UsableItem.Builder builder = UsableItem.Builder.newInstance();
            builder.setName("foo bar");
            builder.setDescription(ITEM_DESCRIPTION);
            builder.setUseText(ITEM_USE_TEXT);
            Item item = itemService.create(builder);
        } else {
            assertTrue("Actionable not instance of Create.", false);
        }
    }
}
