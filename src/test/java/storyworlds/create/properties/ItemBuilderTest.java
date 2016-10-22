package storyworlds.create.properties;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import storyworlds.action.Create;
import storyworlds.exception.UncreateableException;
import storyworlds.gameplay.AbstractMapGameplayTest;
import storyworlds.model.implementation.UsableItem;
import storyworlds.service.ItemService;
import storyworlds.service.message.Message;

/**
 * Created by nvaughan on 10/13/2016.
 */
public class ItemBuilderTest extends AbstractMapGameplayTest {

    @Autowired
    private ItemService itemService;

    @Before
    public void createMessage() {
        Message message = new Message();
        message.setPlayer(user);
        itemService = new ItemService();
    }

    @Test(expected = UncreateableException.class)
    public void nullMessageThrowsExceptionTest() throws UncreateableException {
        UsableItem.Builder props = UsableItem.Builder.newInstance();
        props.setName("aa").setDescription("bb").setUseText("cc");
        itemService.create(props);
    }

    @Test(expected = UncreateableException.class)
    public void badItemPropertiesTest() throws Exception {
        UsableItem.Builder builder = UsableItem.Builder.newInstance();
        builder.setName("aa").setUseText(null).setDescription("bb");
        itemService.create(builder);
    }


    @Test(expected = UncreateableException.class)
    public void badItemPropertiesTest2() throws Exception {
        UsableItem.Builder props = UsableItem.Builder.newInstance();
        props.setName("").setUseText("").setDescription("");
        ItemService itemService = new ItemService();
        itemService.create(props);
    }
}
