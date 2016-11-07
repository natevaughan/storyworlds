package storyworlds.create;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import storyworlds.exception.UncreateableException;
import storyworlds.gameplay.AbstractMapGameplayTest;
import storyworlds.model.Item;
import storyworlds.model.builder.UsableItemBuilder;
import storyworlds.service.ItemService;

/**
 * Created by nvaughan on 10/7/2016.
 */
public class ItemCreatorTest extends AbstractMapGameplayTest {

    @Autowired
    ItemService itemService;

    private final String ITEM_NAME = "arbitrary.item.name";
    private final String ITEM_DESCRIPTION = "arbitrary.item.description";
    private final String ITEM_USE_TEXT = "arbitrary.item.use.text";

    @Test(expected = UncreateableException.class)
    public void twoWordNameTest() throws Exception {
        UsableItemBuilder builder = new UsableItemBuilder();
        builder.setName("foo bar");
        builder.setDescription(ITEM_DESCRIPTION);
        builder.setUseText(ITEM_USE_TEXT);
        Item item = itemService.create(builder.build());
    }
}
