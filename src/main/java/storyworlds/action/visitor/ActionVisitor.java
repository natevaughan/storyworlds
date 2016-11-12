package storyworlds.action.visitor;

import storyworlds.action.Help;
import storyworlds.action.Map;
import storyworlds.action.Move;
import storyworlds.action.Quit;
import storyworlds.action.Status;
import storyworlds.action.Take;
import storyworlds.action.Use;
import storyworlds.exception.InvalidDirectionException;
import storyworlds.exception.InvalidLinkException;
import storyworlds.exception.NotFoundException;

public interface ActionVisitor {
    void visit(Help help);
    void visit(Move move) throws InvalidLinkException, InvalidDirectionException;
    void visit(Map map);
    void visit(Quit quit);
    void visit(Status status);
    void visit(Take take) throws NotFoundException;
    void visit(Use use);
}
