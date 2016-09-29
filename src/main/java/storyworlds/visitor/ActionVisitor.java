package storyworlds.visitor;

import storyworlds.action.*;
import storyworlds.action.Error;

public interface ActionVisitor {
    public void visit(Create create);
    public void visit(Error error);
    public void visit(Edit edit);
    public void visit(Help help);
    public void visit(Move move);
    public void visit(Map map);
    public void visit(Status status);
    public void visit(Take take);
    public void visit(Quit quit);
    public void visit(Use use);
}
