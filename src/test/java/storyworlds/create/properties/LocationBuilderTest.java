package storyworlds.create.properties;

import org.junit.Ignore;
import org.junit.Test;
import storyworlds.exception.UncreateableException;
import storyworlds.gameplay.AbstractMapGameplayTest;
import storyworlds.model.Location;
import storyworlds.model.implementation.ImmutableLocation;
import storyworlds.model.implementation.WikiStoryworld;
import storyworlds.model.implementation.builder.ImmutableLocationBuilder;

import static org.junit.Assert.assertNotNull;

public class LocationBuilderTest extends AbstractMapGameplayTest {

    @Test
    public void testGoodLocationProperties() throws UncreateableException {
        Location location = new ImmutableLocationBuilder().setDescription("aa").setStoryworld(new WikiStoryworld()).setCreator(user).build();
        assertNotNull(location);
    }

    @Test(expected = UncreateableException.class)
    public void testBadLocationProperties() throws UncreateableException {
        Location location = new ImmutableLocationBuilder().setDescription("aa").build();
    }
}
