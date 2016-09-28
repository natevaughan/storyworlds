package storyworlds.gameplay;

import org.junit.Before;
import storyworlds.model.Player;
import storyworlds.model.implementation.User;
import storyworlds.service.message.MessageService;

/**
 * Created by nvaughan on 9/23/2016.
 */
public abstract class AbstractGameplayTest {

    protected MessageService messageService;
    protected Player user;

    @Before
    public void setup() {
        user = new User("foo");
        messageService = new MessageService();
    }
}
