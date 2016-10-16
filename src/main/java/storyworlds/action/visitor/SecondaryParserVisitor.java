package storyworlds.action.visitor;

import storyworlds.action.*;
import storyworlds.action.Error;
import storyworlds.action.parser.DirectionParser;
import storyworlds.create.CreatableFactory;
import storyworlds.create.Createable;
import storyworlds.model.enumeration.Direction;

/**
 * Responsibilities:
 * -Parsing directions or items
 * -Noting unrecognized modifiers for valid actions that do not require secondary
 */
public class SecondaryParserVisitor implements ActionVisitor {

    private String secondary = null;

    public void visit(Create create) {
        getCreateArgs(create);
    }

    public void visit(Delete delete) {
        getCreateArgs(delete);
    }

    public void visit(Error error) {
        setUnrecognizedModifier(error);
    }

    public void visit(Edit edit) {
        getCreateArgs(edit);
    }

    public void visit(Help help) {
        setUnrecognizedModifier(help);
    }

    public void visit(Status status) {
        setUnrecognizedModifier(status);
    }

    public void visit(Move move) {
        if (Direction.ERROR.equals(DirectionParser.parse(secondary))) {
            setUnrecognizedModifier(move);
        }
        move.setDirection(DirectionParser.parse(secondary));
    }

    public void visit(Map map) {
        setUnrecognizedModifier(map);
    }

    public void visit(Take take) {
        take.setItemName(secondary.trim());
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
            setUnrecognizedModifier(actionable, secondary);
        }
    }

    private void setUnrecognizedModifier(Actionable actionable, String modifier) {
        actionable.getMessage().addLine("Unreconginzed modifier: " + modifier);
    }

    private void getCreateArgs(CreateableAction actionable) {
        if (secondary != null) {
            String[] createArgs = secondary.split("\\s+");
            if (createArgs.length > 0) {
                actionable.setCreateable(CreatableFactory.parse(createArgs[0]));
                if (Createable.ERROR.equals(actionable.getCreateable())) {
                    setUnrecognizedModifier(actionable, createArgs[0]);
                }
            }
            if (createArgs.length > 1) {
                actionable.setDirection(DirectionParser.parse(createArgs[1]));
                if (Direction.ERROR.equals(actionable.getDirection())) {
                    setUnrecognizedModifier(actionable, createArgs[1]);
                }
            }
        }
    }
}
