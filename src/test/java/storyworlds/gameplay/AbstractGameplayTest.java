package storyworlds.gameplay;

import org.junit.Before;
import storyworlds.factory.MapFactory;
import storyworlds.model.Location;
import storyworlds.model.Player;
import storyworlds.model.implementation.User;

/**
 * Created by nvaughan on 9/23/2016.
 */
public abstract class AbstractGameplayTest {

    protected Executor executor;
    protected Player user;

    @Before
    public void setup() {
        user = new User("foo");
        executor = new Executor(user);
    }
}
