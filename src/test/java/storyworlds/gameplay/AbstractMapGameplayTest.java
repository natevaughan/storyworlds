package storyworlds.gameplay;

import org.junit.Before;
import storyworlds.initial.map.MapFactory;
import storyworlds.model.Location;
import storyworlds.model.Progress;
import storyworlds.model.Storyworld;
import storyworlds.model.implementation.StoryworldProgress;
import storyworlds.model.implementation.WikiStoryworld;

/**
 * Created by nvaughan on 9/27/2016.
 */
public class AbstractMapGameplayTest extends AbstractGameplayTest {

    protected Location start;

    protected Storyworld storyworld;

    @Before
    public void setup() {
        super.setup();
        storyworld = new WikiStoryworld();
        storyworld.setEntry(MapFactory.getStartMap(user));
        Progress progress = new StoryworldProgress(storyworld);
        user.setCurrentProgress(progress);
        start = user.getCurrentProgress().getLocation();
    }
}
