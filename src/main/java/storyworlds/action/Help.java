package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;

/**
 * Created by nvaughan on 9/27/2016.
 */
public class Help extends AbstractAction {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

}
