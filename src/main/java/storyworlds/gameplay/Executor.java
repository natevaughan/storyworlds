package storyworlds.gameplay;

import java.util.Map;

import storyworlds.model.Action;
import storyworlds.model.Direction;
import storyworlds.model.Link;
import storyworlds.model.Messenger;
import storyworlds.model.Player;
import storyworlds.model.implementation.ConsoleMessenger;

public class Executor {
    
//  @Autowired Environment env;
    
    private final Player          player;
    private final ActionParser actionp;
    private final DirectionParser dirp;
    private final Messenger       m;

    public Executor(Player player) {
        this.player = player;
        this.actionp = new ActionParser();
        this.dirp = new DirectionParser();
        this.m = new ConsoleMessenger();
    }

    public Action execute(String args) {
        String[] arg = split(args);
        Action action = Action.ERROR;
        Direction direction = Direction.ERROR;
        
        if (arg.length > 0) {
            action = actionp.parse(arg[0]);
        }
        
        if (arg.length  > 1) {
            direction = dirp.parse(arg[0]);
                
        }
        
        return execute(action);
        
    }

    private Action execute(Action action) {

        
        String[] arg = {"abc"};
        Direction direction = Direction.ERROR;
        
        if (Action.LOOK.equals(action)) {
            if (arg.length > 1) {
                direction = dirp.parse(arg[1]);
                m.addLine("you " + action.toString() + " " + direction.toString());
                m.addLine(player.getLocation().getLink(direction).getText(player));
            }
            if (arg.length <= 1 || Direction.ERROR.equals(direction)) {
                action = Action.ERROR;
            }
        } else if (Action.MOVE.equals(action)) {
            direction = dirp.parse(arg[1]);
            if (!Direction.ERROR.equals(direction)) {
                player.setLocation(player.getLocation().getLink(direction).getLinkedLocation(player.getLocation()));
                m.addLine(player.getLocation().getText());
                for (Map.Entry<Direction, Link> entry : player.getLocation().getLinks().entrySet()) {
                    if (entry.getValue().isPassable(player)) {
                        m.addLine("To the " + entry.getKey() + " is " + entry.getValue().getText(player));
                    }
                }
            }
        } else {
            m.addLine("Unhandled action: " + action);
        }
        m.send();
        return action;
    }

    public static String[] split(String input) {
        return input.split("[ ]+");
    }

    public Player getPlayer() {
        return player;
    }
}
