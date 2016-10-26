package storyworlds.action.visitor;

import storyworlds.action.Create;
import storyworlds.action.Delete;
import storyworlds.action.Edit;
import storyworlds.action.Error;
import storyworlds.action.Help;
import storyworlds.action.Map;
import storyworlds.action.Move;
import storyworlds.action.Quit;
import storyworlds.action.Status;
import storyworlds.action.Take;
import storyworlds.action.Use;
import storyworlds.exception.BadLinkException;

public interface ActionVisitor {
    public void visit(Create create);
    public void visit(Delete delete);
    public void visit(Error error);
    public void visit(Edit edit);
    public void visit(Help help);
    public void visit(Move move) throws BadLinkException;
    public void visit(Map map);
    public void visit(Status status);
    public void visit(Take take);
    public void visit(Quit quit);
    public void visit(Use use);
}
