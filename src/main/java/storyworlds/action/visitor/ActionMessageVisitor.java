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
import storyworlds.message.ConsoleMessenger;
import storyworlds.message.Messenger;
import storyworlds.model.Direction;
import storyworlds.model.Link;
import storyworlds.model.Player;

public class ActionMessageVisitor implements ActionVisitor {

//    Messenger messenger = getBean(env.getProperty(MESSENGER_TYPE));
    Messenger m = new ConsoleMessenger();
    
    private Player player;

    public ActionMessageVisitor(Player player) {
        this.player = player;
    }

    public void visit(Create create) {
        // TODO Auto-generated method stub

    }

    public void visit(Error error) {
        // TODO Auto-generated method stub

    }

    public void visit(Items items) {
        // TODO Auto-generated method stub

    }

    public void visit(Look look) {
        // TODO Auto-generated method stub
    }

    public void visit(Map map) {
        // TODO Auto-generated method stub

    }

    public void visit(Move move) {
        m.addLine(player.getLocation().getText());
        m.send();
    }

    public void visit(Quit quit) {
        // TODO Auto-generated method stub

    }

    public void visit(Take take) {
        // TODO Auto-generated method stub

    }

    public void visit(Use use) {
        // TODO Auto-generated method stub

    }
}
