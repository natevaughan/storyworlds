package storyworlds.action.visitor;

import storyworlds.action.Create;
import storyworlds.action.Error;
import storyworlds.action.Status;
import storyworlds.action.Map;
import storyworlds.action.Move;
import storyworlds.action.Quit;
import storyworlds.action.Take;
import storyworlds.action.Use;
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
        error.setMessage("Error");
    }

    public void visit(Status status) {
        status.setMessage("Selfcheck");
        status.setSuccessful(true);
    }

    public void visit(Map map) {

    }

    public void visit(Move move) {

        move.setSuccessful(false);
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
    }

    public void visit(Take take) {
        if (player.getLocation().getItem(take.getItemName()) == null) {
            take.setSuccessful(false);
            take.setMessage("Item not found: " + take.getItemName());
            return;
        }
        player.addItem(player.getLocation().takeItem(take.getItemName()));
    }

    public void visit(Use use) {
        if (player.getItem(use.getItemName()) != null) {
            player.listItems().stream().forEach(item -> item.setActive(false));
            Item item = player.getItem(use.getItemName());
            item.setActive(true);
            use.setSuccessful(true);
            use.setMessage(item.getUseMessage());
        }
    }
}
