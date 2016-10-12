package storyworlds.create;

import org.junit.Test;
import storyworlds.action.Actionable;
import storyworlds.action.Create;
import storyworlds.create.properties.ItemProperties;
import storyworlds.exception.UncreateableException;
import storyworlds.gameplay.AbstractMapGameplayTest;
import storyworlds.model.Item;
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
    public void createItemTest() throws UncreateableException {
        Actionable a = messageService.process(new Message(user, "create item"));
        assertTrue(a.isSuccessful());
        if (a instanceof Create) {
            ItemService itemService = new ItemService();
            ItemProperties itemProperties = new ItemProperties();
            itemProperties.setName(ITEM_NAME);
            itemProperties.setDescription(ITEM_DESCRIPTION);
            itemProperties.setUseText(ITEM_USE_TEXT);
            ((Create) a).setProperties(itemProperties);
            Item item = itemService.create((Create) a);
            assertNotNull(item);
        } else {
            assertTrue("Actionable not instance of Create.", false);
        }
    }

    @Test(expected = UncreateableException.class)
    public void twoWordNameTest() throws UncreateableException {
        Actionable a = messageService.process(new Message(user, "create item"));

        if (a instanceof Create) {
            ItemService itemService = new ItemService();
            ItemProperties itemProperties = new ItemProperties();
            itemProperties.setName("foo bar");
            itemProperties.setDescription(ITEM_DESCRIPTION);
            itemProperties.setUseText(ITEM_USE_TEXT);
            ((Create) a).setProperties(itemProperties);
            Item item = itemService.create((Create) a);
        } else {
            assertTrue("Actionable not instance of Create.", false);
        }
    }
}
