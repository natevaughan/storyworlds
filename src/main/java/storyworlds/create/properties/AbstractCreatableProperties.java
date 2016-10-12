package storyworlds.create.properties;

/**
 * Created by nvaughan on 10/11/2016.
 */
public abstract class AbstractCreatableProperties implements Validateable {

    protected String description;

    public String getDescription() {
        return description;
    }

    public AbstractCreatableProperties setDescription(String description) {
        this.description = description;
        return this;
    }
}
