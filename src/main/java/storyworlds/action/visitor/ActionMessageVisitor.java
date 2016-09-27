package storyworlds.action.visitor;


import storyworlds.action.*;
import storyworlds.action.Error;
import storyworlds.model.Direction;
import storyworlds.model.Item;
import storyworlds.model.Link;
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
        m.send(error.getMessage());
    }

    public void visit(Status status) {
        describeLocation();
        if (player.listItems().isEmpty()) {
            m.addLine("You have no items");
        } else {
            m.addLine("Your inventory: ");
            for (Item item : this.player.listItems()) {
                m.addLine(item.getName());
            }
        }
        m.send();
    }

    public void visit(storyworlds.action.Map map) {
        m.send("Map feature not yet supported");
    }

    public void visit(Move move) {
        m.addLine(move.getMessage());
        if (move.isSuccessful()) {
            describeLocation();
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
        if (use.isSuccessful()) {
            m.addLine("Using " + use.getItemName());
        } else {
            m.addLine("You don't have a " + use.getItemName());
        }
        m.send();
    }

    private void describeLocation() {
        m.addLine(player.getLocation().getText());
        java.util.Map<Direction, Link> links = player.getLocation().getLinks();
        for (Direction direction : links.keySet()) {
            m.addLine("To the " + direction + " is " + links.get(direction).getDescription());
        }
        for (Item item : player.getLocation().listItems()) {
            m.addLine("There is a " + item.getName() + " here");
        }
    }
}
