package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;
import storyworlds.create.CreateableType;

public class Edit extends AbstractCreateableAction {

    public void accept(ActionVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    public boolean isCreateable() {
        if (getDirection() == null) {
            return CreateableType.LOCATION.equals(creatableType);
        } else {
            return creatableType != null
                    && !CreateableType.LOCATION.equals(creatableType)
                    && getDirection() != null;
        }
    }


}
