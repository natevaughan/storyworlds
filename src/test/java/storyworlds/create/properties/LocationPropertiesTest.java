package storyworlds.create.properties;

import org.junit.Test;
import storyworlds.gameplay.AbstractMapGameplayTest;

public class LocationPropertiesTest extends AbstractMapGameplayTest {

    @Test
    public void testGoodLocationProperties() {
        LocationProperties locationProperties = new LocationProperties();
        locationProperties.setDescription("aa");
    }

    @Test
    public void testBadLocationProperties() {

    }
}
