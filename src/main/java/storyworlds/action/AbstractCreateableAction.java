package storyworlds.action;

import storyworlds.create.CreateableType;
import storyworlds.create.properties.Validateable;

public abstract class AbstractCreateableAction extends DirectionedAction implements CreateableAction {

    protected Validateable   properties;
    protected CreateableType creatableType;

    public CreateableType getCreateableType() {
        return creatableType;
    }

    public void setCreateable(CreateableType creatableType) {
        this.creatableType = creatableType;
    }

    public Validateable getProperties() {
        return properties;
    }

    public void setProperties(Validateable properties) {
        this.properties = properties;
    }
}
