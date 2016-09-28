package storyworlds.visitor;

import storyworlds.action.*;
import storyworlds.create.Createables;
import storyworlds.create.LocationFactory;
import storyworlds.model.*;
import storyworlds.model.Error;
import storyworlds.model.implementation.Links;
import storyworlds.service.message.ConsoleMessenger;
import storyworlds.service.message.Messenger;

/**
 * Responsibilities
 * -Executing action
 * -Setting action success
 * -sending message
 */
public class ActionDoVisitor implements ActionVisitor {

    private Player player;
    private Messenger m;


    public ActionDoVisitor(Player player) {
        this.player = player;
        m = new ConsoleMessenger(player);
    }

    public void visit(Create create) {
        if (Direction.ERROR.equals(create.getDirection()) || Createables.ERROR.equals(create.getCreatable())) {
            return;
        }

        m.sendMessage("What would you like the text of your location to say?");
        String mainText = m.getMessage().getText();
        m.addLine("How would you like to link to this location? Link types include:");

        for (Links l : Links.values()) {
            m.addLine(l + ": " + l.getDescription());
        }
        m.sendMessage();


        LocationFactory locationCreator = new LocationFactory(player);

//        locationCreator.setLocationType(create.getCreatable());
//        locationCreator.getText();

        create.setSuccessful(true);
    }

    public void visit(Error error) {
        m.sendMessage(error.getMessage());
    }

    public void visit(Edit edit) {

    }

    public void visit(Help help) {
        m.addLine("Valid actions: ");
        for (Action action : Action.values()) {
            m.addLine(action.toString());
        }
        m.sendMessage();
        help.setSuccessful(true);
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
        m.sendMessage();
        status.setSuccessful(true);
    }

    public void visit(Map map) {
        m.sendMessage("Map feature not yet supported");
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

        m.addLine(move.getMessage());
        describeLocation();
        m.sendMessage();

    }

    public void visit(Quit quit) {
        quit.setSuccessful(true);
        m.addLine("Thanks for playing.");
        m.sendMessage();
        System.exit(1);
    }

    public void visit(Take take) {
        if (player.getLocation().getItem(take.getItemName()) == null) {
            take.setMessage("Item not found: " + take.getItemName());
            m.addLine("Unable to take the " + take.getItemName());
            return;
        }
        take.setSuccessful(true);
        player.addItem(player.getLocation().takeItem(take.getItemName()));
        m.addLine("You take the " + take.getItemName());
        m.sendMessage();
    }

    public void visit(Use use) {
        if (player.getItem(use.getItemName()) == null) {
            m.addLine("You don't have a " + use.getItemName());
            return;
        }

        use.setSuccessful(true);
        player.listItems().stream().forEach(item -> item.setActive(false));
        Item item = player.getItem(use.getItemName());
        item.setActive(true);
        m.addLine(item.getUseMessage());
        m.sendMessage();
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
