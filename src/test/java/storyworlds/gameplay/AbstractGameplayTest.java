package storyworlds.gameplay;

import org.junit.Before;
import storyworlds.AbstractSpringTest;
import storyworlds.model.Player;
import storyworlds.model.implementation.Player2;
import storyworlds.service.message.MessageService;

/**
 * Created by nvaughan on 9/23/2016.
 */
public abstract class AbstractGameplayTest extends AbstractSpringTest {

    protected MessageService messageService;
    protected Player user;

    @Before
    public void setup() {
        user = new Player2("foo", "a", "b");
        messageService = new MessageService();
    }
}
