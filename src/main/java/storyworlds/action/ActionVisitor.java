package storyworlds.action;

public interface ActionVisitor {
    public void visit(Move move);
    public void visit(Quit quit);
    public void visit(Error error);
    public void visit(Map map);
    public void visit(Look look);
    public void visit(Take take);
    public void visit(Use use);
    public void visit(Items items);
}
