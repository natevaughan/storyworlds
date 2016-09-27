package storyworlds.gameplay;

import org.junit.Test;
import storyworlds.action.Actionable;

import static org.junit.Assert.assertNotNull;

public class ExecutorTest extends AbstractMapGameplayTest {

    @Test
    public void executorCanaryTest() {
        Actionable a = executor.execute("foo");
        assertNotNull("Executor should return an actionable", a);
    }
}
