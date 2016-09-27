package storyworlds.gameplay;

import storyworlds.action.ActionFactory;
import storyworlds.action.Actionable;
import storyworlds.visitor.ActionDoVisitor;
import storyworlds.visitor.ActionMessageVisitor;
import storyworlds.visitor.SecondaryParserVisitor;
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

    public Actionable execute(String input) {

        input = input.trim();

        String primary = null;
        String secondary = null;

        if (!input.isEmpty()) {
            if (input.contains(" ")) {
                primary = input.substring(0, input.indexOf(" "));
                secondary = input.substring(input.indexOf(" ") + 1);
            } else {
                primary = input;
            }
        }

        Actionable actionable = ActionFactory.get(primary);

        secondaryParser.setSecondary(secondary);

        actionable.accept(secondaryParser);
        
        actionable.accept(actionDoVisitor);
        
        actionable.accept(actionMessageVisitor);
        
        return actionable;
    }
}
