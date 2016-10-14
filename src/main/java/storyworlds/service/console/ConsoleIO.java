package storyworlds.service.console;

import storyworlds.action.*;
import storyworlds.action.Error;
import storyworlds.action.visitor.ActionVisitor;
import storyworlds.constants.GameTextConstants;
import storyworlds.create.properties.DirectionalLinkProperties;
import storyworlds.create.properties.ItemProperties;
import storyworlds.create.properties.LocationProperties;
import storyworlds.exception.UncreateableException;
import storyworlds.initial.map.MapFactory;
import storyworlds.model.Location;
import storyworlds.model.Player;
import storyworlds.model.implementation.User;
import storyworlds.service.ItemService;
import storyworlds.service.LinkService;
import storyworlds.service.LocationService;
import storyworlds.service.message.Message;
import storyworlds.service.message.MessageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleIO implements ActionVisitor, GameTextConstants {

    private StringBuilder sb = new StringBuilder();
    MessageService messageService = new MessageService();
    LocationService locationService = new LocationService();
    LinkService linkService = new LinkService();
    ItemService itemService = new ItemService();
    private Scanner scanner = new Scanner(System.in);
    private Player player;

    public void run() {
        sendMessage(WELCOME_MESSAGE);
        String name = getCommand();
        Location start = MapFactory.getStartMap();
        player = new User(name);
        player.setLocation(start);
        Actionable response = messageService.process(new Message(player, "status"));
        sendMessage(response.getMessage().getText());
        while (!Quit.class.equals(response.getClass())) {
            sendMessage("What is your next move?");
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

        create.getMessage().resetText();
        switch (create.getCreateable()) {
            case LOCATION:
                LocationProperties locationProps = new LocationProperties();
                DirectionalLinkProperties directionalLinkProperties = new DirectionalLinkProperties();
                sendMessage("What would you like the text describing the link to the location to say? \n" +
                        "It should complete this sentence: " + create.getDirection().formatted() + " there is...");
//                create.getMessage().getFields().put(KEY_LINK_DESCRIPTION, getCommand());
                directionalLinkProperties.setDescription(getCommand());
                sendMessage("What would you like the text of the link to be while the user moves to the new location?");
//                create.getMessage().getFields().put(KEY_LINK_PASS_TEXT, getCommand());
                directionalLinkProperties.setPassText(getCommand());
                sendMessage("What would you like the text of the location to be once the user arrives?");
//                create.getMessage().getFields().put(KEY_LOCATION_TEXT, getCommand());
                locationProps.setDescription(getCommand());
                create.setProperties(locationProps);
                try {
                    Location location = locationService.build(create);
                    directionalLinkProperties.setToLocation(location)
                            .setFromLocation(player.getLocation());
                    create.setProperties(directionalLinkProperties);
                    linkService.create(create);
                } catch (UncreateableException e) {
                    sendMessage(e.getMessage());
                }
                break;
            case LINK:
                DirectionalLinkProperties linkProperties = new DirectionalLinkProperties();
                linkProperties.setFromLocation(player.getLocation());
                sendMessage("What would you like the text describing the link to say? \n" +
                        "It should complete this sentence: " + create.getDirection().formatted() + " there is...");
                linkProperties.setDescription(getCommand());
                sendMessage("What would you like the text of the link to be while the user moves to the new location?");
                linkProperties.setPassText(getCommand());
                sendMessage("Which location would you like to connect the link to?");
                int i = 1;
                List<Location> locationHistory = new ArrayList<>(create.getMessage().getPlayer().getLocationHistory());
                for (Location loc: locationHistory) {
                    String[] text = loc.getDescription().split("\n");
                    int length = (text[0].length() > 30) ? 30 : text[0].length();
                    String abbrev = text[0].substring(0, length)  + "...";
                    sendMessage("previous Location " + i + ": " + abbrev);
                    i++;
                }
                sendMessage("Enter the number of the desired location:");
                try {
                    Integer index  = Integer.parseInt(getCommand());
                    if (index >= i || index < 1) {
                        sendMessage("Invalid number");
                    } else {
                        linkProperties.setToLocation(locationHistory.get(index - 1));
                    }
                } catch (NumberFormatException nfe) {
                    sendMessage("invalid number");
                }
                create.setProperties(linkProperties);
                try {
                    linkService.create(create);
                } catch (UncreateableException e) {
                    sendMessage(e.getMessage());
                }
                break;
            case ITEM:
                ItemProperties itemProperties = new ItemProperties();
                sendMessage("What would you like the one-word name of the item to be?");
                itemProperties.setName(getCommand());
                sendMessage("What would you like the description of the item to be?");
                itemProperties.setDescription(getCommand());
                sendMessage("What would you like the description of using the item to be?");
                itemProperties.setUseText(getCommand());
                create.setProperties(itemProperties);
                try {
                    itemService.create(create);
                } catch (UncreateableException e) {
                    sendMessage(e.getMessage());
                }
                break;
            default:
                break;
        }
    }

    public void visit(Error error) {

    }

    public void visit(Edit edit) {
        if (!edit.isCreateable()) {
            return;
        }
        edit.getMessage().resetText();
        switch(edit.getCreateable()) {
            case LOCATION:
                LocationProperties locationProperties = new LocationProperties();
                sendMessage("What would you like the text of the location to be once the user arrives?");
                locationProperties.setDescription(getCommand());
                edit.setProperties(locationProperties);
                try {
                    locationService.edit(edit);
                } catch (UncreateableException e) {
                    sendMessage(e.getMessage());
                }
                break;
            case LINK:
                DirectionalLinkProperties directionalLinkProperties = new DirectionalLinkProperties();
                sendMessage("What would you like the text describing the link to the location to say? \n" +
                        "It should complete this sentence: " + edit.getDirection().formatted() + " there is...");
                directionalLinkProperties.setDescription(getCommand());
                sendMessage("What would you like the text of the link to be while the user moves to the new location?");
                directionalLinkProperties.setPassText(getCommand());
                directionalLinkProperties.setFromLocation(player.getLocation());
                directionalLinkProperties.setToLocation(player.getLocation().getOutboundLink(edit.getDirection()).getToLocation());
                edit.setProperties(directionalLinkProperties);
                try {
                    linkService.edit(edit);
                } catch (UncreateableException e) {
                    sendMessage(e.getMessage());
                }
                break;
            default:
                sendMessage("invalid creatable");
                break;
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
