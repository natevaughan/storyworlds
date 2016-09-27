package storyworlds.action.visitor;

import storyworlds.action.*;
import storyworlds.action.Error;
import storyworlds.action.parser.DirectionParser;
import storyworlds.model.Direction;
import storyworlds.model.Player;

/**
 * Responsibilities:
 * -Parsing directions or items
 * -Noting unrecognized modifiers for valid actions that do not require secondary
 */
public class SecondaryParserVisitor implements ActionVisitor {

    private String secondary = null;

    private final Player player;

    private final DirectionParser dirp = new DirectionParser();
    
    public SecondaryParserVisitor(Player player) {
        this.player = player;
    }

    public void visit(Create create) {
    }
    
    public void visit(Error error) {
        if (secondary != null) {
            error.setMessage(error.getMessage() + ", unreconginzed modifier: " + secondary);
        }
    }

    public void visit(Edit edit) {

    }

    public void visit(Status status) {
        setUnrecognizedModifier(status);
    }

    public void visit(Move move) {
        if (Direction.ERROR.equals(dirp.parse(secondary))) {
            setUnrecognizedModifier(move);
        }
        move.setDirection(dirp.parse(secondary));
    }
    public void visit(Map map) {
        setUnrecognizedModifier(map);
    }

    public void visit(Take take) {
        take.setItemName(secondary);
    }

    public void visit(Quit quit) {
        setUnrecognizedModifier(quit);
    }

    public void visit(Use use) {
        use.setItemName(secondary);
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    private void setUnrecognizedModifier(Actionable actionable) {
        if (secondary != null) {
            actionable.setMessage("Unreconginzed modifier: " + secondary);
        }
    }
}
