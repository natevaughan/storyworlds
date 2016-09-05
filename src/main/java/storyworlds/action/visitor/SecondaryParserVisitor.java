package storyworlds.action.visitor;

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

public class SecondaryParserVisitor implements ActionVisitor {

    private final String secondary;

    private final DirectionParser dirp = new DirectionParser();

    private final ItemParser itemParser = new ItemParser();
    
    public SecondaryParserVisitor(String secondary) {
        this.secondary = secondary;
    }
    
    public void visit(Error error) {
        // TODO Auto-generated method stub
        
    }

    public void visit(Items items) {
        // TODO Auto-generated method stub
        
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
        // TODO Auto-generated method stub
        
    }

    public void visit(Quit quit) {
        // TODO Auto-generated method stub
        
    }

    public void visit(Use use) {
        // TODO Auto-generated method stub
        
    }
}
