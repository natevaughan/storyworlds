package storyworlds.action;

import storyworlds.create.Createable;
import storyworlds.create.properties.Validateable;

public abstract class AbstractCreateableAction extends DirectionedAction implements CreateableAction {

    protected Validateable properties;
    protected Createable creatable;

    public Createable getCreateable() {
        return creatable;
    }

    public void setCreateable(Createable creatable) {
        this.creatable = creatable;
    }

    public Validateable getProperties() {
        return properties;
    }

    public void setProperties(Validateable properties) {
        this.properties = properties;
    }
}
