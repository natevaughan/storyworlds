package storyworlds.create;

import org.junit.Test;
import storyworlds.action.Actionable;
import storyworlds.action.Create;
import storyworlds.constants.PropertyKeys;
import storyworlds.exception.UncreateableItemException;
import storyworlds.gameplay.AbstractMapGameplayTest;
import storyworlds.model.Item;
import storyworlds.service.ItemService;
import storyworlds.service.message.Message;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by nvaughan on 10/7/2016.
 */
public class ItemCreatorTest extends AbstractMapGameplayTest implements PropertyKeys {

    private final String ITEM_NAME = "arbitrary.item.name";
    private final String ITEM_DESCRIPTION = "arbitrary.item.description";
    private final String ITEM_USE_TEXT = "arbitrary.item.use.text";


    @Test
    public void createItemTest() throws UncreateableItemException {
        Actionable a = messageService.process(new Message(user, "create item"));
        assertTrue(a.isSuccessful());
        assertTrue(a instanceof Create);

        ItemService itemService = new ItemService();
        a.getMessage().getFields().put(KEY_ITEM_NAME, ITEM_NAME);
        a.getMessage().getFields().put(KEY_ITEM_DESCRIPTION, ITEM_DESCRIPTION);
        a.getMessage().getFields().put(KEY_ITEM_USE_TEXT, ITEM_USE_TEXT);
        Item item = itemService.create((Create) a);
        assertNotNull(item);
    }

    @Test(expected = UncreateableItemException.class)
    public void twoWordNameTest() throws UncreateableItemException {
        Actionable a = messageService.process(new Message(user, "create item"));

        ItemService itemService = new ItemService();
        a.getMessage().getFields().put(KEY_ITEM_NAME, "foo bar");
        a.getMessage().getFields().put(KEY_ITEM_DESCRIPTION, ITEM_DESCRIPTION);
        a.getMessage().getFields().put(KEY_ITEM_USE_TEXT, ITEM_USE_TEXT);
        Item item = itemService.create((Create) a);
    }
}
