package storyworlds.gameplay;

import storyworlds.model.*;
import storyworlds.model.implementation.ConsoleMessenger;

public class Executor {
    
    private final Player player;
    private final DirectionParser dirp;
    private final Messenger m;

    public Executor (Player player) {
        this.player = player;
        this.dirp = new DirectionParser();
        this.m = new ConsoleMessenger();
    }
    
    public Action execute(String args) {
        String[] arg = split(args); 
        Action action = Action.ERROR;
        Direction direction = Direction.ERROR;
        if (arg.length > 0) {
            action = ActionParser.parse(arg[0]);
        }
        if(Action.LOOK.equals(action)) {
            if (arg.length > 1) {
                direction = dirp.parse(arg[1]);
                m.addLine("you " + action.toString() + " " + direction.toString());
                m.addLine(player.getLocation().getLink(direction).getText(player));
            }
            if (arg.length <=1 || Direction.ERROR.equals(direction)) {
                action = Action.ERROR;
            }
        } else if (Action.MOVE.equals(action)) {
            direction = dirp.parse(arg[1]);
                player.setLocation(player.getLocation().getLink(direction).getLinkedLocation(player.getLocation()));
                m.addLine(player.getLocation().getText());
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
