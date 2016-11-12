package storyworlds.create.properties;

import org.junit.Test;
import storyworlds.exception.UncreateableException;
import storyworlds.gameplay.AbstractMapGameplayTest;
import storyworlds.model.Location;
import storyworlds.model.builder.ImmutableLocationBuilder;
import storyworlds.model.implementation.WikiStoryworld;

import static org.junit.Assert.assertNotNull;

public class LocationBuilderTest extends AbstractMapGameplayTest {

    @Test
    public void testGoodLocationProperties() throws UncreateableException {
        Location location = new ImmutableLocationBuilder().setDescription("aa").setStoryworld(new WikiStoryworld(user)).build();
        assertNotNull(location);
    }

    @Test(expected = UncreateableException.class)
    public void testBadLocationProperties() throws UncreateableException {
        Location location = new ImmutableLocationBuilder().setDescription("aa").build();
    }
}
