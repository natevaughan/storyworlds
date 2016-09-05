package storyworlds.action.visitor;

import storyworlds.action.Actionable;
import storyworlds.action.Error;
import storyworlds.action.Items;
import storyworlds.action.Look;
import storyworlds.action.Map;
import storyworlds.action.Move;
import storyworlds.action.Quit;
import storyworlds.action.Take;
import storyworlds.action.Use;
import storyworlds.model.Direction;
import storyworlds.model.Link;
import storyworlds.model.Player;

public class ActionDoVisitor implements ActionVisitor {

    private Player player;
    
    public ActionDoVisitor(Player player){
        this.player = player;
    }
    
    public void visit(Move move) {
        
        if (Direction.ERROR.equals(move.getDirection())) {
            return;
        }
        
        Link link = player.getLocation().getLink(move.getDirection());
        
        if (!link.isPassable(player)) {
            return;
        }
        
        player.setLocation(player.getLocation().getLink(move.getDirection()).getLinkedLocation(player.getLocation()));
    }
    
    public void visit(Quit quit) {
        // TODO Auto-generated method stub
        
    }

    public void visit(Error error) {
        // TODO Auto-generated method stub
        
    }
    
    public void visit(Actionable actionable) {
        // unknown?
    }

    public void visit(Map map) {
        // TODO Auto-generated method stub
        
    }

    public void visit(Look look) {
        // TODO Auto-generated method stub
    }

    public void visit(Take take) {
        // TODO Auto-generated method stub
        
    }

    public void visit(Use use) {
        // TODO Auto-generated method stub
        
    }

    public void visit(Items items) {
        // TODO Auto-generated method stub
        
    }
}
