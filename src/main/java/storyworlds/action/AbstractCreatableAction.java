package storyworlds.action;

import storyworlds.create.Createables;

/**
 * Created by nvaughan on 9/28/2016.
 */
public abstract class AbstractCreatableAction extends DirectionedAction {

    private Createables creatable;

    public Createables getCreatable() {
        return creatable;
    }

    public void setCreatable(Createables creatable) {
        this.creatable = creatable;
    }

}
