package storyworlds.action.visitor;

import storyworlds.action.Create;
import storyworlds.action.Delete;
import storyworlds.action.Edit;
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
    void visit(Create create) throws Exception;
    void visit(Delete delete) throws Exception;
    void visit(Edit edit) throws Exception;
    void visit(Help help) throws Exception;
    void visit(Move move) throws Exception;
    void visit(Map map) throws Exception;
    void visit(Quit quit) throws Exception;
    void visit(Status status) throws Exception;
    void visit(Take take) throws Exception;
    void visit(Use use) throws Exception;
}
