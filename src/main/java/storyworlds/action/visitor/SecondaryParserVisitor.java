package storyworlds.action.visitor;

import storyworlds.action.Create;
import storyworlds.action.Error;
import storyworlds.action.Items;
import storyworlds.action.Look;
import storyworlds.action.Map;
import storyworlds.action.Move;
import storyworlds.action.Quit;
import storyworlds.action.Take;
import storyworlds.action.Use;
import storyworlds.action.parser.DirectionParser;
import storyworlds.action.parser.ItemParser;
import storyworlds.model.Item;
import storyworlds.model.Player;

import java.util.Collection;

public class SecondaryParserVisitor implements ActionVisitor {

    private String secondary;

    private final Player player;

    private final DirectionParser dirp = new DirectionParser();
    
    public SecondaryParserVisitor(Player player) {
        this.player = player;
    }

    public void visit(Create create) {
        // TODO Auto-generated method stub
        
    }
    
    public void visit(Error error) {
        // TODO Auto-generated method stub
        
    }

    public void visit(Items items) {
        // no action
    }

    public void visit(Move move) {
        move.setDirection(dirp.parse(secondary));
    }

    public void visit(Look look) {
        look.setDirection(dirp.parse(secondary));
        
    }

    public void visit(Map map) {
        // TODO Auto-generated method stub
        
    }

    public void visit(Take take) {
        take.setItemName(secondary);
        if (player.getLocation().getItem(secondary) != null) {
            take.setSuccessful(true);
        }
    }

    public void visit(Quit quit) {
        // TODO Auto-generated method stub
        
    }

    public void visit(Use use) {
        use.setItemName(secondary);
        if (player.getItem(secondary) != null) {
            use.setSuccessful(true);
        }
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }
}
