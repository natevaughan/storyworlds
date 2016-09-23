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
import storyworlds.model.Direction;
import storyworlds.model.Link;
import storyworlds.model.Player;

public class ActionDoVisitor implements ActionVisitor {

    private Player player;

    public ActionDoVisitor(Player player) {
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

        if (Direction.ERROR.equals(move.getDirection())) {
            return;
        }

        Link link = player.getLocation().getLink(move.getDirection());

        if (!link.isPassable(player)) {
            move.setMessage("Unable to move " + move.getDirection() + ": " + link.getText(player));
            return;
        }

        move.setMessage("Moving " + link.getText(player));
        player.setLocation(player.getLocation().getLink(move.getDirection()).getLinkedLocation(player.getLocation()));
    }

    public void visit(Quit quit) {
        // TODO Auto-generated method stub

    }

    public void visit(Take take) {
        player.addItem(player.getLocation().takeItem(take.getItemName()));
    }

    public void visit(Use use) {
        if (player.getItem(use.getItemName()) != null) {
            player.listItems().stream().forEach(item -> item.setActive(false));
            player.getItem(use.getItemName()).setActive(true);
        }
    }
}
