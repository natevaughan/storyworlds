package storyworlds.visitor;

import storyworlds.action.*;
import storyworlds.action.parser.DirectionParser;
import storyworlds.create.CreatableFactory;
import storyworlds.model.Direction;

/**
 * Responsibilities:
 * -Parsing directions or items
 * -Noting unrecognized modifiers for valid actions that do not require secondary
 */
public class SecondaryParserVisitor implements ActionVisitor {

    private String secondary = null;

    private final DirectionParser dirp = new DirectionParser();

    public void visit(Create create) {
        String[] createArgs = secondary.split("\\s+");
        if (createArgs.length > 0) {
            create.setCreatable(CreatableFactory.parse(createArgs[0]));
        }
        if (createArgs.length > 1) {
            create.setDirection(dirp.parse(createArgs[1]));
        }
    }
//
//    public void visit(Error error) {
//        if (secondary != null) {
//            error.getMessage().addLine("Unreconginzed modifier: " + secondary);
//        }
//    }

    public void visit(Edit edit) {
        String[] createArgs = secondary.split("\\s+");
        if (createArgs.length > 0) {
            edit.setCreatable(CreatableFactory.parse(createArgs[0]));
        }
        if (createArgs.length > 1) {
            edit.setDirection(dirp.parse(createArgs[1]));
        }
    }

    public void visit(Help help) {
        setUnrecognizedModifier(help);
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
            actionable.getMessage().addLine("Unreconginzed modifier: " + secondary);
        }
    }
}
