package storyworlds.action.visitor;

import storyworlds.action.*;
import storyworlds.action.Error;
import storyworlds.constants.PropertyKeys;
import storyworlds.create.Createables;
import storyworlds.model.Direction;
import storyworlds.model.Item;
import storyworlds.model.Link;
import storyworlds.service.message.Message;

import java.util.Collection;

/**
 * Responsibilities
 * -Executing action
 * -Setting action success
 * -Setting message text
 */
public class ActionDoVisitor implements ActionVisitor, PropertyKeys {

    public void visit(Create create) {
        if (Direction.ERROR.equals(create.getDirection()) || Createables.ERROR.equals(create.getCreatable())) {
            return;
        }

        create.getMessage().addLine("OK, creating " + create.getCreatable() + " to the " + create.getDirection());

    }

    public void visit(Error error) {
//        error.getCommand().addLine(error.getCommand());
    }

    public void visit(Edit edit) {
        if (Direction.ERROR.equals(edit.getDirection()) || Createables.ERROR.equals(edit.getCreatable())) {
            return;
        }

        edit.getMessage().addLine("OK, editing " + edit.getCreatable() + " to the " + edit.getDirection());
    }

    public void visit(Help help) {
        help.getMessage().addLine("Valid actions: ");
        for (Action action : Action.values()) {
            help.getMessage().addLine(action.toString());
        }
        help.setSuccessful(true);
    }

    public void visit(Status status) {
        describeLocation(status.getMessage());
        if (status.getMessage().getPlayer().listItems().isEmpty()) {
            status.getMessage().addLine("You have no items");
        } else {
            status.getMessage().addLine("Your inventory: ");
            for (Item item : status.getMessage().getPlayer().listItems()) {
                status.getMessage().addLine(item.getName());
            }
        }
        status.setSuccessful(true);
    }

    public void visit(Map map) {
        map.getMessage().addLine("Map feature not yet supported");
    }

    public void visit(Move move) {

        if (Direction.ERROR.equals(move.getDirection())) {
            move.getMessage().addLine("Invalid direction");
            return;
        }

        Link link = move.getMessage().getPlayer().getLocation().getLink(move.getDirection());

        if (link == null) {
            move.getMessage().addLine("Nothing to the " + move.getDirection());
            return;
        }

        move.getMessage().addLine(move.getMessage().getPlayer().getLocation().getLink(move.getDirection()).getPassText(move.getMessage().getPlayer()));

        if (!link.isPassable(move.getMessage().getPlayer())) {
            return;
        }

        move.setSuccessful(true);

        move.getMessage().getPlayer().setLocation(move.getMessage().getPlayer().getLocation().getLink(move.getDirection()).getToLocation());
        describeLocation(move.getMessage());
    }

    public void visit(Quit quit) {
        quit.setSuccessful(true);
        quit.getMessage().addLine("Thanks for playing.");
    }

    public void visit(Take take) {
        if (take.getMessage().getPlayer().getLocation().getItem(take.getItemName()) == null) {
            take.getMessage().addLine("Item not found: " + take.getItemName());
            return;
        }
        take.setSuccessful(true);
        take.getMessage().getPlayer().addItem(take.getMessage().getPlayer().getLocation().takeItem(take.getItemName()));
        take.getMessage().addLine("You take the " + take.getItemName());
    }

    public void visit(Use use) {
        Collection<Item> items = use.getMessage().getPlayer().listItems();

        if (use.getItemName() == null) {
            use.getMessage().addLine("You don't have a " + use.getItemName());
            return;
        }

        for (Item i : items) {
            if (i.getName().equals(use.getItemName())) {
                use.getMessage().getPlayer().activate(i);
                use.setSuccessful(true);
                use.getMessage().addLine(i.getUseMessage());
            }
        }
    }

    private void describeLocation(Message m) {
        m.addLine(m.getPlayer().getLocation().getText());
        java.util.Map<Direction, Link> links = m.getPlayer().getLocation().getLinks();
        for (Direction direction : links.keySet()) {
            m.addLine("To the " + direction + " is " + links.get(direction).getDescription());
        }
        for (Item item : m.getPlayer().getLocation().listItems()) {
            m.addLine("There is a " + item.getName() + " here");
        }
    }

}
