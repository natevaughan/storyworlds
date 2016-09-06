package storyworlds.action.visitor;

import storyworlds.action.Create;
import storyworlds.action.Error;
import storyworlds.action.Items;
import storyworlds.action.Look;
import storyworlds.action.Map;
import storyworlds.action.Move;
import storyworlds.action.Quit;
import storyworlds.action.Take;
import storyworlds.action.Use;

public interface ActionVisitor {
    public void visit(Create create);
    public void visit(Error error);
    public void visit(Items items);
    public void visit(Move move);
    public void visit(Look look);
    public void visit(Map map);
    public void visit(Take take);
    public void visit(Quit quit);
    public void visit(Use use);
}
