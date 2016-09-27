package storyworlds.gameplay;

import storyworlds.action.ActionFactory;
import storyworlds.action.Actionable;
import storyworlds.action.visitor.ActionDoVisitor;
import storyworlds.action.visitor.ActionMessageVisitor;
import storyworlds.action.visitor.SecondaryParserVisitor;
import storyworlds.model.Player;

public class Executor {
    
//  @Autowired Environment env;

    private final ActionDoVisitor actionDoVisitor;
    private final ActionMessageVisitor actionMessageVisitor;
    private final SecondaryParserVisitor secondaryParser;

    public Executor(Player player) {
        this.actionDoVisitor = new ActionDoVisitor(player);
        secondaryParser = new SecondaryParserVisitor(player);
        this.actionMessageVisitor = new ActionMessageVisitor(player);
    }

    public Actionable execute(String args) {
        
        String[] input = split(args);
        String primary = null;
        String secondary = null;

        if (input.length > 0) {
            primary = input[0];
        }
        
        if (input.length > 1) {
            secondary = input[1];
        }

        Actionable actionable = ActionFactory.get(primary);

        secondaryParser.setSecondary(secondary);

        actionable.accept(secondaryParser);
        
        actionable.accept(actionDoVisitor);
        
        actionable.accept(actionMessageVisitor);
        
        return actionable;
    }

    public static String[] split(String input) {
        return input.split("[ ]+");
    }
}
