package storyworlds.create.properties;

import org.junit.Before;
import org.junit.Test;
import storyworlds.action.Create;
import storyworlds.exception.UncreateableException;
import storyworlds.gameplay.AbstractMapGameplayTest;
import storyworlds.model.Player;
import storyworlds.service.ItemService;
import storyworlds.service.message.Message;

/**
 * Created by nvaughan on 10/13/2016.
 */
public class ItemPropertiesTest extends AbstractMapGameplayTest {

    private Message message;
    private ItemService itemService;

    @Before
    public void createMessage() {
        Message message = new Message();
        message.setPlayer(user);
        itemService = new ItemService();
    }

    @Test(expected = UncreateableException.class)
    public void nullMessageThrowsExceptionTest() throws UncreateableException {
        ItemProperties props = new ItemProperties();
        props.setName("aa").setDescription("bb").setUseText("cc");
        Create create = new Create();
        create.setProperties(props);
        itemService.create(create);
    }

    @Test(expected = UncreateableException.class)
    public void badItemPropertiesTest() throws Exception {
        ItemProperties props = new ItemProperties();
        props.setName("aa").setUseText(null).setDescription("bb");
        ItemService itemService = new ItemService();
        Create create = new Create();
        create.setProperties(props);
        itemService.create(create);
    }


    @Test(expected = UncreateableException.class)
    public void badItemPropertiesTest2() throws Exception {
        ItemProperties props = new ItemProperties();
        props.setName("").setUseText("").setDescription("");
        ItemService itemService = new ItemService();
        Create create = new Create();
        create.setMessage(message);
        create.setProperties(props);
        itemService.create(create);
    }
}
