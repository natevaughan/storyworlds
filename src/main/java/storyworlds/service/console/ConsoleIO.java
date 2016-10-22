package storyworlds.service.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storyworlds.action.Actionable;
import storyworlds.action.Create;
import storyworlds.action.Delete;
import storyworlds.action.Edit;
import storyworlds.action.Error;
import storyworlds.action.Help;
import storyworlds.action.Map;
import storyworlds.action.Move;
import storyworlds.action.Quit;
import storyworlds.action.Status;
import storyworlds.action.Take;
import storyworlds.action.Use;
import storyworlds.action.visitor.ActionVisitor;
import storyworlds.constants.GameTextConstants;
import storyworlds.create.Createable;
import storyworlds.exception.BadLinkException;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Item;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.Storyworld;
import storyworlds.model.enumeration.Direction;
import storyworlds.model.implementation.DirectionalLink;
import storyworlds.model.implementation.ImmutableLocation;
import storyworlds.model.implementation.Player2;
import storyworlds.model.implementation.UsableItem;
import storyworlds.model.implementation.WikiStoryworld;
import storyworlds.model.implementation.persistence.LocationRepository;
import storyworlds.model.implementation.persistence.Player2Repo;
import storyworlds.model.implementation.persistence.StoryworldRepository;
import storyworlds.service.ItemService;
import storyworlds.service.LinkService;
import storyworlds.service.LocationService;
import storyworlds.service.message.Message;
import storyworlds.service.message.MessageService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

@Service
public class ConsoleIO implements ActionVisitor, GameTextConstants {

    @Autowired
    StoryworldRepository storyworldRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    LocationService locationService;

    @Autowired
    LinkService linkService;

    @Autowired
    ItemService itemService;

    @Autowired
    Player2Repo player2Repo;

    private StringBuilder sb = new StringBuilder();
    MessageService messageService = new MessageService();
    private Scanner scanner = new Scanner(System.in);
    private Player2 player;

    public void run() {
        sendMessage("reset?");
        if (ConfirmationParser.parse(getCommand())) {
            reset();
        }
        sendMessage("login");
        login();

        Actionable response = null;
        try {
            response = messageService.process(new Message(player, "status"));
        } catch (BadLinkException e) {
            sendMessage(e.getMessage());
        }
        sendMessage(response.getMessage().getText());
        while (!Quit.class.equals(response.getClass())) {
            try {
                sendMessage("What is your next move?");
                response = messageService.process(new Message(player, getCommand()));
                sendMessage(response.getMessage().getText());
                response.accept(this);
            } catch (BadLinkException e) {
                sendMessage(e.getMessage());
            }
        }
    }

    private void login() {
        sendMessage("Enter your email address:");
        String email = getCommand();
        sendMessage("Enter your password");
        String pass = getCommand();
        player = player2Repo.findByEmailAndPassword(email, pass);
    }

    private void reset() {
        storyworldRepository.deleteAll();
        locationRepository.deleteAll();
        player2Repo.deleteAll();
        createNewPlayer();
        Storyworld storyworld = new WikiStoryworld();
        storyworldRepository.save(storyworld);
        Location start = new ImmutableLocation("no description", storyworld, new HashSet<>(), null);
        locationRepository.save(start);
        storyworld.setEntry(start);
        storyworldRepository.save(storyworld);
        player.setCurrentStoryworld(storyworld);
        player.setLocation(storyworld.getEntry());
        player2Repo.save(player);
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

    private void createNewPlayer() {
        sendMessage(WELCOME_MESSAGE);
        String name = getCommand();
        sendMessage("Please enter your email:");
        String email = getCommand();
        sendMessage("Please enter your password:");
        String password = getCommand();

        player = new Player2(name, email, password);
    }

    public void visit(Create create) {
        if (!create.isCreateable()) {
            return;
        }

        switch (create.getCreateable()) {
            case LOCATION:
                ImmutableLocation.Builder locationBuilder = ImmutableLocation.Builder.newInstance();
                DirectionalLink.Builder linkBuilder = DirectionalLink.Builder.newInstance();
                sendMessage("What would you like the text describing the link to the location to say? \n" +
                        "It should complete this sentence: " + create.getDirection().formatted() + " there is...");
                linkBuilder.setDescription(getCommand());
                sendMessage("What would you like the text of the link to be while the user moves to the new location?");
                linkBuilder.setPassText(getCommand());
                sendMessage("What would you like the text of the location to be once the user arrives?");
                locationBuilder.setDescription(getCommand())
                    .setStoryworld(player.getCurrentStoryworld());
                try {
                    Location location = locationService.create(locationBuilder);
                    linkBuilder.setToLocation(location);
                    Link link = linkService.create(linkBuilder);
                    player.getLocation().addOutboundLink(create.getDirection(), link);
                    locationService.update(player.getLocation());

                } catch (UncreateableException e) {
                    sendMessage(e.getMessage());
                }
                break;
            case LINK:
                DirectionalLink.Builder directionalLinkBuilder = DirectionalLink.Builder.newInstance();
                sendMessage("What would you like the text describing the link to say? \n" +
                        "It should complete this sentence: " + create.getDirection().formatted() + " there is...");
                directionalLinkBuilder.setDescription(getCommand());
                sendMessage("What would you like the text of the link to be while the user moves to the new location?");
                directionalLinkBuilder.setPassText(getCommand());
                sendMessage("Which location would you like to connect the link to?");
                int i = 1;
                List<Location> locationHistory = new ArrayList<>(create.getMessage().getPlayer().getLocationHistory());
                for (Location loc: locationHistory) {
                    if (loc.isActive()) {
                        String[] text = loc.getDescription().split("\n");
                        int length = (text[0].length() > 30) ? 30 : text[0].length();
                        String abbrev = text[0].substring(0, length) + "...";
                        sendMessage("previous Location " + i + ": " + abbrev);
                        i++;
                    }
                }
                sendMessage("Enter the number of the desired location:");
                try {
                    Integer index  = Integer.parseInt(getCommand());
                    if (index >= i || index < 1) {
                        sendMessage("Invalid number");
                    } else {
                        directionalLinkBuilder.setToLocation(locationHistory.get(index - 1));
                    }
                } catch (NumberFormatException nfe) {
                    sendMessage("invalid number");
                }
                try {
                    Link link = linkService.create(directionalLinkBuilder);
                    player.getLocation().addOutboundLink(create.getDirection(), link);
                    locationService.update(player.getLocation());
                } catch (UncreateableException e) {
                    sendMessage(e.getMessage());
                }
                break;
            case ITEM:
                UsableItem.Builder itemBuilder = UsableItem.Builder.newInstance();
                sendMessage("What would you like the one-word name of the item to be?");
                itemBuilder.setName(getCommand());
                sendMessage("What would you like the description of the item to be?");
                itemBuilder.setDescription(getCommand());
                sendMessage("What would you like the description of using the item to be?");
                itemBuilder.setUseText(getCommand());
                try {
                    Item item = itemService.create(itemBuilder);
                    player.getLocation().addItem(item);
                    locationService.update(player.getLocation());
                } catch (UncreateableException e) {
                    sendMessage(e.getMessage());
                }
                break;
            default:

                break;
        }
    }

    public void visit(Delete delete) {
        sendMessage("Are you sure you want to delete " + delete.getCreateable() + " " + delete.getDirection() + "?");
        if (ConfirmationParser.parse(getCommand())) {
            if (Createable.LOCATION.equals(delete.getCreateable())) {
                locationService.delete(delete);
            }
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
                ImmutableLocation.Builder locationBuilder = ImmutableLocation.Builder.newInstance();
                sendMessage("What would you like the text of the location to be once the user arrives?");
                locationBuilder.setDescription(getCommand());
                Location formerLocation = edit.getMessage().getPlayer().getLocation();
                locationBuilder.setPreviousLocation(formerLocation);
                locationBuilder.setStoryworld(edit.getMessage().getPlayer().getCurrentStoryworld());
                try {

                    Location location = locationService.create(locationBuilder);
                    cloneLinks(location, formerLocation);
                    locationRepository.save(location);

                    // retire formerLocation
                    formerLocation.setActive(false);
                    formerLocation.setForwardingLocation(location);
                    locationService.update(formerLocation);

                    player.setLocation(location);
                    player2Repo.save(player);
                } catch (UncreateableException e) {
                    sendMessage(e.getMessage());
                }
                break;
            case LINK:
                DirectionalLink.Builder linkBuilder = DirectionalLink.Builder.newInstance();
                sendMessage("What would you like the text describing the link to the location to say? \n" +
                        "It should complete this sentence: " + edit.getDirection().formatted() + " there is...");
                linkBuilder.setDescription(getCommand());
                sendMessage("What would you like the text of the link to be while the user moves to the new location?");
                linkBuilder.setPassText(getCommand());
                linkBuilder.setToLocation(player.getLocation().getOutboundLink(edit.getDirection()).getToLocation());
                try {
                    Link link = linkService.create(linkBuilder);
                    edit.getMessage().getPlayer().getLocation().addOutboundLink(edit.getDirection(), link);
                    locationService.update(edit.getMessage().getPlayer().getLocation());
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
        player2Repo.save(player);
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

    public void cloneLinks(Location location, Location formerLocation) {
        for (Direction direction : formerLocation.getOutboundLinks().keySet()) {
            location.addOutboundLink(direction, formerLocation.getOutboundLink(direction));
        }
    }
}
