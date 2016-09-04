package storyworlds.action;

import storyworlds.gameplay.DirectionParser;
import storyworlds.gameplay.ItemParser;
import storyworlds.model.Direction;
import storyworlds.model.Link;
import storyworlds.model.Player;

public class ActionDoVisitor implements ActionVisitor {

    private Player player;

    private String secondary;
    
    private final DirectionParser dirp = new DirectionParser();

    private final ItemParser itemParser = new ItemParser();
    
    public ActionDoVisitor(Player player){
        this.player = player;
    }
    
    public void visit(Move move) {
        
        Direction direction = parseDirection();
        
        if (Direction.ERROR.equals(direction)) {
            return;
        }
        
        Link link = player.getLocation().getLink(direction);
        
        if (!link.isPassable(player)) {
            return;
        }
        
        player.setLocation(player.getLocation().getLink(direction).getLinkedLocation(player.getLocation()));
    }
    
    public void visit(Quit quit) {
        // TODO Auto-generated method stub
        
    }

    public void visit(Error error) {
        // TODO Auto-generated method stub
        
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

    private Direction parseDirection() {
        return dirp.parse(secondary);
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }
}
