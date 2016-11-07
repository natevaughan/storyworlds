package storyworlds.create.properties;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import storyworlds.exception.UncreateableException;
import storyworlds.gameplay.AbstractMapGameplayTest;
import storyworlds.model.builder.UsableItemBuilder;
import storyworlds.model.implementation.UsableItem;
import storyworlds.service.ItemService;
import storyworlds.service.message.Message;

/**
 * Created by nvaughan on 10/13/2016.
 */
@Ignore
public class ItemBuilderTest extends AbstractMapGameplayTest {

    @Autowired
    private ItemService itemService;

    @Before
    public void createMessage() {
        Message message = new Message();
        message.setPlayer(user);
    }

    @Test(expected = UncreateableException.class)
    public void nullMessageThrowsExceptionTest() throws UncreateableException {
        UsableItemBuilder props = new UsableItemBuilder();
        props.setName("aa").setDescription("bb").setUseText("cc");
        props.build();
    }

    @Test(expected = UncreateableException.class)
    public void badItemPropertiesTest() throws Exception {
        UsableItemBuilder builder = new UsableItemBuilder();
        builder.setName("aa").setUseText(null).setDescription("bb");
        builder.build();
    }


    @Test(expected = UncreateableException.class)
    public void badItemPropertiesTest2() throws Exception {
        UsableItemBuilder props = new UsableItemBuilder();
        props.setName("").setUseText("").setDescription("");
        props.build();
    }
}
