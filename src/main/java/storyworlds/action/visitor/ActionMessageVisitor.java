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
import storyworlds.model.Item;
import storyworlds.model.Player;
import storyworlds.service.message.ConsoleMessenger;
import storyworlds.service.message.Messenger;

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
        m.addLine("Unrecognized action: " + error.getMessage());
    }

    public void visit(Items items) {
        if (player.listItems().isEmpty()) {
            m.addLine("No items");
        } else {
            m.addLine("Current inventory: ");
            for (Item item : this.player.listItems()) {
                m.addLine(item.getName());
            }
        }
        m.send();
    }

    public void visit(Look look) {
        m.addLine(player.getLocation().getLink(look.getDirection()).getText(player));
        m.send();
    }

    public void visit(Map map) {
        // TODO Auto-generated method stub

    }

    public void visit(Move move) {
        m.addLine(player.getLocation().getText());
        for (Item item : player.getLocation().listItems()) {
            m.addLine("There is a " + item.getName() + " here");
        }
        m.send();
    }

    public void visit(Quit quit) {
        m.addLine("Thanks for playing.");
        m.send();
    }

    public void visit(Take take) {
        if (take.isSuccessful()) {
            m.addLine("You take the " + take.getItemName());
        } else {
            m.addLine("Unable to take the " + take.getItemName());
        }
        m.send();
    }

    public void visit(Use use) {
        // TODO Auto-generated method stub

    }
}
