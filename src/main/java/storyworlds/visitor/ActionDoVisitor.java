package storyworlds.visitor;

import storyworlds.action.*;
import storyworlds.model.Error;
import storyworlds.model.Direction;
import storyworlds.model.Item;
import storyworlds.model.Link;
import storyworlds.model.Player;

/**
 * Responsibilities
 * -Executing action
 * -Setting action success
 * -Setting message
 */
public class ActionDoVisitor implements ActionVisitor {

    private Player player;

    public ActionDoVisitor(Player player) {
        this.player = player;
    }

    public void visit(Create create) {

    }

    public void visit(Error error) {

    }

    public void visit(Edit edit) {

    }

    public void visit(Help help) {
        help.setSuccessful(true);
    }

    public void visit(Status status) {
        status.setMessage("Status");
        status.setSuccessful(true);
    }

    public void visit(Map map) {
    }

    public void visit(Move move) {

        if (Direction.ERROR.equals(move.getDirection())) {
            move.setMessage("Invalid direction");
            return;
        }

        Link link = player.getLocation().getLink(move.getDirection());

        if (link == null) {
            move.setMessage("Nothing to the " + move.getDirection());
            return;
        }

        move.setMessage(link.getPassText(player));

        if (!link.isPassable(player)) {
            return;
        }

        move.setSuccessful(true);
        player.setLocation(player.getLocation().getLink(move.getDirection()).getLinkedLocation(player.getLocation()));
    }

    public void visit(Quit quit) {
        quit.setSuccessful(true);
        quit.setMessage("Thanks for playing");
        System.exit(1);
    }

    public void visit(Take take) {
        if (player.getLocation().getItem(take.getItemName()) == null) {
            take.setMessage("Item not found: " + take.getItemName());
            return;
        }
        take.setSuccessful(true);
        player.addItem(player.getLocation().takeItem(take.getItemName()));
    }

    public void visit(Use use) {
        if (player.getItem(use.getItemName()) == null) {
            use.setMessage("You don't have item: " + use.getItemName());
            return;
        }

        use.setSuccessful(true);

        player.listItems().stream().forEach(item -> item.setActive(false));
        Item item = player.getItem(use.getItemName());
        item.setActive(true);
        use.setMessage(item.getUseMessage());
    }
}
