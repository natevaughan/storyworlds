package storyworlds.action;

import storyworlds.create.Createables;
import storyworlds.visitor.ActionVisitor;

public class Create extends DirectionedAction {

    private Createables creatable;

    public Createables getCreatable() {
        return creatable;
    }

    public void setCreatable(Createables creatable) {
        this.creatable = creatable;
    }

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }
}
