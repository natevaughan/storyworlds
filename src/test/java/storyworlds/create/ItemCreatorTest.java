package storyworlds.create;

import org.junit.Test;
import storyworlds.exception.UncreateableException;
import storyworlds.gameplay.AbstractMapGameplayTest;
import storyworlds.model.Item;
import storyworlds.model.implementation.UsableItem;
import storyworlds.service.ItemService;

/**
 * Created by nvaughan on 10/7/2016.
 */
public class ItemCreatorTest extends AbstractMapGameplayTest {

    private final String ITEM_NAME = "arbitrary.item.name";
    private final String ITEM_DESCRIPTION = "arbitrary.item.description";
    private final String ITEM_USE_TEXT = "arbitrary.item.use.text";

    @Test(expected = UncreateableException.class)
    public void twoWordNameTest() throws Exception {
        ItemService itemService = new ItemService();
        UsableItem.Builder builder = UsableItem.Builder.newInstance();
        builder.setName("foo bar");
        builder.setDescription(ITEM_DESCRIPTION);
        builder.setUseText(ITEM_USE_TEXT);
        Item item = itemService.create(builder);
    }

}
