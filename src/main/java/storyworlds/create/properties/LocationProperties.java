package storyworlds.create.properties;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by nvaughan on 10/10/2016.
 */
public class LocationProperties extends AbstractCreatableProperties {

    public boolean isValid() {
        return this.description != null;
    }
}
