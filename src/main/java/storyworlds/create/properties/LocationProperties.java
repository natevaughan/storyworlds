package storyworlds.create.properties;

import java.util.Collection;

/**
 * Created by nvaughan on 10/10/2016.
 */
public class LocationProperties extends AbstractCreatableProperties {

    private Collection<ItemProperties> items;

    public boolean isValid() {
        return this.description != null;
    }
}
