package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;

/**
 * Created by nvaughan on 10/13/2016.
 */
public class Delete extends AbstractCreateableAction  {

    public void accept(ActionVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    @Override
    public boolean isCreateable() {
        return creatable != null
                && getDirection() != null;
    }
}
