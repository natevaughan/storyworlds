package storyworlds.gameplay;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import storyworlds.AbstractSpringTest;
import storyworlds.model.implementation.IdentifiedPlayer;
import storyworlds.service.message.MessageService;

/**
 * Created by nvaughan on 9/23/2016.
 */
public abstract class AbstractGameplayTest extends AbstractSpringTest {

    @Autowired
    protected MessageService messageService;

    protected IdentifiedPlayer user;

    @Before
    public void setup() {
        user = new IdentifiedPlayer("foo", "a", "b");
    }
}
