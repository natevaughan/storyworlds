package storyworlds.gameplay;

import storyworlds.action.Action;
import storyworlds.action.ActionDoVisitor;
import storyworlds.action.Actionable;
import storyworlds.model.Player;

public class Executor {
    
//  @Autowired Environment env;
    
    private final ActionParser actionParser;
    private final ActionDoVisitor actionDoVisitor;

    public Executor(Player player) {
        this.actionDoVisitor = new ActionDoVisitor(player);
        this.actionParser = new ActionParser();
        // this.m = getBean(env.getMessengerType);
    }

    public void execute(String args) {
        
        String[] input = split(args);
        String primary = null;
        String secondary = null;

        if (input.length > 0) {
            primary = input[0];
        }
        
        if (input.length > 1) {
            secondary = input[1];
        }

        Actionable actionable = actionParser.parse(primary);
        
        actionDoVisitor.setSecondary(secondary);
        
        actionable.accept(actionDoVisitor);
    }

    private void execute(Actionable actionable) {

//        if (Action.LOOK.equals(action)) {
//                direction = dirp.parse(args[1]);
//                m.addLine("you " + action.toString() + " " + direction.toString());
//                m.addLine(player.getLocation().getLink(direction).getText(player));
//            
//            if (args.length <= 1 || Direction.ERROR.equals(direction)) {
//                action = Action.ERROR;
//            }
//        } else if (Action.MOVE.equals(action)) {
//            direction = dirp.parse(args[1]);
//            if (!Direction.ERROR.equals(direction)) {
//                m.addLine(player.getLocation().getText());
//                for (Map.Entry<Direction, Link> entry : player.getLocation().getLinks().entrySet()) {
//                    if (entry.getValue().isPassable(player)) {
//                        m.addLine("To the " + entry.getKey() + " is " + entry.getValue().getText(player));
//                    }
//                }
//            }
//        } else {
//            m.addLine("Unhandled action: " + action);
//        }
//        m.send();
    }

    public static String[] split(String input) {
        return input.split("[ ]+");
    }
}
