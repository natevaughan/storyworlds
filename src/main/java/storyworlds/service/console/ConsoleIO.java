package storyworlds.service.console;

import storyworlds.action.*;
import storyworlds.action.Error;
import storyworlds.action.visitor.ActionVisitor;
import storyworlds.constants.GameTextConstants;
import storyworlds.factory.MapFactory;
import storyworlds.model.Location;
import storyworlds.model.Player;
import storyworlds.model.enumeration.Links;
import storyworlds.model.enumeration.Locations;
import storyworlds.model.implementation.User;
import storyworlds.service.LinkService;
import storyworlds.service.LocationService;
import storyworlds.service.message.Message;
import storyworlds.service.message.MessageService;

import java.util.Scanner;

public class ConsoleIO implements ActionVisitor, GameTextConstants {

    private StringBuilder sb = new StringBuilder();
    MessageService messageService = new MessageService();
    LocationService locationService = new LocationService();
    LinkService linkService = new LinkService();
    private Scanner scanner = new Scanner(System.in);
    private Player player;


    public void run() {
        sendMessage(WELCOME_MESSAGE);
        String name = getCommand();
        Location start = MapFactory.getStartMap();
        player = new User(name);
        player.setLocation(start);
        Actionable response = messageService.process(new Message(player, "status"));
        while (!Quit.class.equals(response.getClass())) {
            sendMessage("What's your next move?");
            response = messageService.process(new Message(player, getCommand()));
            sendMessage(response.getMessage().getText());
            response.accept(this);
        }
    }

    public void addLine(String text) {
        sb.append("\n");
        sb.append(text);
    }

    public void sendMessage() {
        System.out.println(sb.toString());
        sb.delete(0, sb.length());
    }

    public void sendMessage(String message) {
        addLine(message);
        sendMessage();
    }

    public String getCommand() {
        return scanner.nextLine();
    }

    public void visit(Create create) {
        if (!create.isCreateable()) {
            return;
        }

        switch (create.getCreateable()) {
            case LOCATION:
                create.getMessage().resetText();
                sendMessage("What would you like the text describing the link to the location to say? \n" +
                        "It should complete this sentence: To the " + create.getDirection() + " there is a...");
                create.getMessage().getFields().put(KEY_LINK_DESCRIPTION, getCommand());
                sendMessage("What would you like the text of the link to be while the user moves to the new location?");
                create.getMessage().getFields().put(KEY_LINK_PASS_TEXT, getCommand());
                sendMessage("What would you like the text of the location to be once the user arrives?");
                create.getMessage().getFields().put(KEY_LOCATION_TEXT, getCommand());
                create.setLinkType(Links.DIRECTIONAL);
                create.setLocationType(Locations.IMMUTABLE);
                locationService.build(create);
                break;
            case LINK:
                create.getMessage().resetText();
                sendMessage("What would you like the text describing the link to say? \n" +
                        "It should complete this sentence: To the " + create.getDirection() + " there is a...");
                create.getMessage().getFields().put(KEY_LINK_DESCRIPTION, getCommand());
                sendMessage("What would you like the text of the link to be while the user moves to the new location?");
                create.getMessage().getFields().put(KEY_LINK_PASS_TEXT, getCommand());
                sendMessage("Which location would you like to connect the link to?");
                int i = 1;
                for (Location loc: create.getMessage().getPlayer().getLocationHistory()) {
                    String[] text = loc.getText().split("\n");
                    int length = (text[0].length() > 30) ? 30 : text[0].length();
                    String abbrev = text[0].substring(0, length)  + "...";
                    sendMessage("previous Location " + i + ": " + abbrev);
                    i++;
                }
                sendMessage("Enter the number of the desired location:");
                try {
                    Integer index  = Integer.parseInt(getCommand());
                    if (index >= i) {
                        sendMessage("Invalid number");
                    } else {
                        create.setLocationIndex(index - 1);
                    }
                } catch (NumberFormatException nfe) {
                    sendMessage("invalid number");
                }
                create.setLinkType(Links.DIRECTIONAL);
                linkService.create(create);
                break;
            default:
                break;
        }

        if (create.isSuccessful()) {
            sendMessage("Successfully created location");
        }
    }

    public void visit(Error error) {

    }

    public void visit(Edit edit) {
        edit.getMessage().resetText();
        switch(edit.getCreateable()) {
            case LOCATION:
                sendMessage("What would you like the text of the location to be once the user arrives?");
                edit.getMessage().getFields().put(KEY_LOCATION_TEXT, getCommand());
                locationService.edit(edit);
            case LINK:
                sendMessage("What would you like the text describing the link to the location to say? \n" +
                        "It should complete this sentence: To the " + edit.getDirection() + " there is a...");
                edit.getMessage().getFields().put(KEY_LINK_DESCRIPTION, getCommand());
                sendMessage("What would you like the text of the link to be while the user moves to the new location?");
                edit.getMessage().getFields().put(KEY_LINK_PASS_TEXT, getCommand());
                linkService.edit(edit);
            case ERROR:
                sendMessage("invalid creatable");
        }
    }

    public void visit(Help help) {

    }

    public void visit(Move move) {

    }

    public void visit(Map map) {

    }

    public void visit(Status status) {

    }

    public void visit(Take take) {

    }

    public void visit(Quit quit) {

    }

    public void visit(Use use) {

    }
}
