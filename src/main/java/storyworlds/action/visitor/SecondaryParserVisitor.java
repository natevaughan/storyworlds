package storyworlds.action.visitor;

import storyworlds.action.Actionable;
import storyworlds.action.Create;
import storyworlds.action.CreateableAction;
import storyworlds.action.Delete;
import storyworlds.action.Edit;
import storyworlds.action.Help;
import storyworlds.action.Map;
import storyworlds.action.Move;
import storyworlds.action.Quit;
import storyworlds.action.Status;
import storyworlds.action.Take;
import storyworlds.action.Use;
import storyworlds.action.parser.DirectionParser;
import storyworlds.create.CreateableParser;
import storyworlds.exception.InvalidDirectionException;
import storyworlds.exception.UncreateableException;

/**
 * Responsibilities:
 * -Parsing directions or items
 * -Noting unrecognized modifiers for valid actions that do not require secondary
 */
public class SecondaryParserVisitor implements ActionVisitor {

    private String secondary = null;

    public void visit(Create create) throws InvalidDirectionException, UncreateableException {
        getCreateArgs(create);
    }

    public void visit(Delete delete) throws InvalidDirectionException, UncreateableException {
        getCreateArgs(delete);
    }

    public void visit(Edit edit) throws InvalidDirectionException, UncreateableException {
        getCreateArgs(edit);
    }

    public void visit(Help help) {
        setUnrecognizedModifier(help);
    }

    public void visit(Status status) {
        setUnrecognizedModifier(status);
    }

    public void visit(Move move) throws InvalidDirectionException {
        move.setDirection(DirectionParser.parse(secondary));
    }

    public void visit(Map map) {
        setUnrecognizedModifier(map);
    }

    public void visit(Quit quit) {}

    public void visit(Take take) {
        take.setItemName(secondary.trim());
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

    private void getCreateArgs(CreateableAction actionable) throws InvalidDirectionException, UncreateableException {
        if (secondary != null) {
            String[] createArgs = secondary.split("\\s+");
            if (createArgs.length > 0) {
                actionable.setCreateable(CreateableParser.parse(createArgs[0]));
            }
            if (createArgs.length > 1) {
                actionable.setDirection(DirectionParser.parse(createArgs[1]));
            }
        }
    }
}
