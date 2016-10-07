package storyworlds.action.visitor;

import storyworlds.action.*;
import storyworlds.action.Error;
import storyworlds.constants.PropertyKeys;
import storyworlds.create.Creatable;
import storyworlds.create.Createables;
import storyworlds.model.Direction;
import storyworlds.model.Item;
import storyworlds.model.Link;
import storyworlds.service.message.Message;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 * Responsibilities
 * -Validating action
 * -Executing action
 * -Setting action success
 * -Setting message text
 */
public class ActionDoVisitor implements ActionVisitor, PropertyKeys {

    public void visit(Create create) {
        StringBuilder sb = new StringBuilder();
        if (create.isCreateable()) {
            sb.append("OK, creating ").append(create.getCreateable()).append(" ").append(create.getDirection());
            create.setSuccessful(true);
        } else {
            sb.append("Unable to create ").append(create.getCreateable()).append("\n");

            if (create.getCreateable() == null || Createables.ERROR.equals(create.getCreateable())) {
                sb.append(enumerateCreatables("create"));
            }
            if (create.getDirection() == null || Direction.ERROR.equals(create.getDirection())) {
                sb.append(enumerateDirections());
            }
            if (create.getMessage().getPlayer().getLocation().getOutboundLink(create.getDirection()) != null) {
                sb.append(create.getCreateable()).append(" already exists ").append(create.getDirection().formatted().toLowerCase());
            }
        }
        create.getMessage().addLine(sb.toString());
    }

    public void visit(Error error) {
    }

    public void visit(Edit edit) {
        StringBuilder sb = new StringBuilder();
        if (edit.isCreateable()) {
            sb.append("OK, editing ");
            if (edit.getDirection() != null) {
                sb.append(edit.getCreateable() + " " + edit.getDirection());
            } else {
                sb.append("this " + edit.getCreateable());
            }
            edit.setSuccessful(true);
        } else {
            if (Createables.LOCATION.equals(edit.getCreateable()) && edit.getDirection() != null) {
                edit.getMessage().addLine("To edit a location, first travel to it.");
            }
        }
        edit.getMessage().addLine(sb.toString());
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

        Link link = move.getMessage().getPlayer().getLocation().getOutboundLink(move.getDirection());

        if (link == null) {
            move.getMessage().addLine("Nothing to the " + move.getDirection());
            return;
        }

        move.getMessage().addLine(move.getMessage().getPlayer().getLocation().getOutboundLink(move.getDirection()).getPassText(move.getMessage().getPlayer()));

        if (!link.isPassable(move.getMessage().getPlayer())) {
            return;
        }

        move.setSuccessful(true);

        move.getMessage().getPlayer().setLocation(move.getMessage().getPlayer().getLocation().getOutboundLink(move.getDirection()).getToLocation());
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
        java.util.Map<Direction, Link> links = m.getPlayer().getLocation().getOutboundLinks();
        for (Direction direction : links.keySet()) {
            m.addLine(direction.formatted() + " is " + links.get(direction).getDescription());
        }
        for (Item item : m.getPlayer().getLocation().listItems()) {
            if (!m.getPlayer().listItems().contains(item)) {
                m.addLine("There is a " + item.getName() + " here");
            }
        }
    }

    private String enumerateDirections() {
        StringBuilder sb = new StringBuilder();
        sb.append("Please specify a direction: ");
        int i = 0;
        for (Direction direction : Direction.values()) {
            if (i > 0)
                sb.append(", ");
            if (!Direction.ERROR.equals(direction))
                sb.append(direction);
            ++i;
        }
        return sb.toString();
    }

    private String enumerateCreatables(String type) {
        StringBuilder sb = new StringBuilder();
        sb.append("Please specify something to ").append(type).append(": ");
        int i = 0;
        for (Createables createables : Createables.values()) {
            if (i > 0)
                sb.append(", ");
            if (!Createables.ERROR.equals(createables))
                sb.append(createables);
            ++i;
        }
        return sb.toString();
    }
}
