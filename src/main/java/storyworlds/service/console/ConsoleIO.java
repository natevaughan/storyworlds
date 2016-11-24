package storyworlds.service.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import storyworlds.action.Actionable;
import storyworlds.action.Create;
import storyworlds.action.Delete;
import storyworlds.action.Edit;
import storyworlds.action.Help;
import storyworlds.action.Map;
import storyworlds.action.Move;
import storyworlds.action.Quit;
import storyworlds.action.Status;
import storyworlds.action.Take;
import storyworlds.action.Use;
import storyworlds.action.visitor.ActionVisitor;
import storyworlds.constants.GameTextConstants;
import storyworlds.create.CreateableType;
import storyworlds.exception.InvalidDirectionException;
import storyworlds.exception.InvalidLinkException;
import storyworlds.exception.NotFoundException;
import storyworlds.exception.UncreateableException;
import storyworlds.exception.UnrecognizedInputException;
import storyworlds.model.Direction;
import storyworlds.model.Item;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.Progress;
import storyworlds.model.Storyworld;
import storyworlds.model.builder.BlockableLinkBuilder;
import storyworlds.model.builder.DirectionalLinkBuilder;
import storyworlds.model.builder.ImmutableLocationBuilder;
import storyworlds.model.builder.LinkBuilder;
import storyworlds.model.builder.UsableItemBuilder;
import storyworlds.model.builder.WikiStoryworldBuilder;
import storyworlds.model.implementation.IdentifiedPlayer;
import storyworlds.model.implementation.StoryworldProgress;
import storyworlds.service.ItemService;
import storyworlds.service.LinkService;
import storyworlds.service.LocationService;
import storyworlds.service.PlayerService;
import storyworlds.service.StoryworldService;
import storyworlds.service.message.Message;
import storyworlds.service.message.MessageService;

@Service
public class ConsoleIO implements ActionVisitor, GameTextConstants {

    @Autowired
    StoryworldService storyworldService;

    @Autowired
    LocationService locationService;

    @Autowired
    ItemService itemService;

    @Autowired
    LinkService linkService;

    @Autowired
    PlayerService playerService;

    @Autowired
    MessageService messageService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private StringBuilder sb = new StringBuilder();
    private Scanner scanner = new Scanner(System.in);
    private IdentifiedPlayer player;

    public void run() throws Exception {
        sendMessage("reset?");
        if (ConfirmationParser.parse(getCommand())) {
            reset();
        }
        sendMessage("login?");
        if (ConfirmationParser.parse(getCommand())) {
            login();
        } else {
            createNewPlayer();
        }

        sendMessage("new storyworld?");
        List<Storyworld> storyworlds = new ArrayList<>(storyworldService.list());
        Storyworld choice;
        Progress progress = null;
        if (storyworlds == null || storyworlds.isEmpty() || ConfirmationParser.parse(getCommand())) {
            sendMessage("CREATE NEW STORYWORLD");
            choice = createNewStoryworld();
        } else {
            choice = storyworlds.get(selectIndex(storyworlds, "Storyworld"));
            for (Progress p : player.getProgress()) {
                if (progress.getStoryworld().equals(choice)) {
                    progress = p;
                }
            }
        }
        if (progress == null) {
            progress = new StoryworldProgress(choice);
        }
        player.setCurrentProgress(progress);
        playerService.createOrUpdate(player);

        Actionable response = null;
        try {
            response = messageService.process(new Message(player, "status"));
        } catch (InvalidLinkException | UnrecognizedInputException | InvalidDirectionException e) {
            sendMessage(e.getMessage());
        }
        sendMessage(response.getMessage().getText());
        while (!Quit.class.equals(response.getClass())) {
            try {
                sendMessage("What is your next move?");
                response = messageService.process(new Message(player, getCommand()));
                sendMessage(response.getMessage().getText());
                response.accept(this);
            } catch (InvalidLinkException | UnrecognizedInputException | InvalidDirectionException e) {
                sendMessage(e.getMessage());
            }
        }
    }

    private int selectIndex(List objects, String objectName) {

        sendMessage("choose a " + objectName + ":");
        int i = 1;
        for (Object obj : objects) {
            sendMessage(i + ": " + obj.toString());
            sendMessage("-----------------");
        }
        sendMessage("Enter your choice:");
        int tries = 0;
        int selection = 0;
        Object choice = null;
        while (choice == null) {
            try {
                selection = Integer.parseInt(getCommand()) - 1;
                choice = objects.get(selection);
            } catch (Exception e) {
                ++tries;
                if (tries > 3) {
                    System.exit(-1);
                }
                sendMessage("Invalid number. Try again.");
            }
        }
        return selection;
    }

    private void login() {
        int i = 0;
        IdentifiedPlayer temp;
        while (player == null && i < 5) {
            ++i;
            sendMessage("Enter your username:");
            String email = getCommand();
            sendMessage("Enter your password");
            String pass = getCommand();
            pass = encoder.encode(pass);
            temp = (IdentifiedPlayer) playerService.getByUsername(email);
            if (temp == null) {
                sendMessage("User not found");
                continue;
            }
            if (temp.getPassword().equals(pass)) {
                player = temp;
            } else {
                sendMessage("Incorrect password");
            }
        }
    }

    private void reset() {
        storyworldService.deleteAll();
        locationService.deleteAll();
        playerService.deleteAll();
        itemService.deleteAll();
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
        try {
            player = new IdentifiedPlayer(name, email, password).build();
            playerService.create(player);
        } catch (UncreateableException e) {
            sendMessage(e.getMessage());
        }
    }

    private Storyworld createNewStoryworld() throws UncreateableException {
        WikiStoryworldBuilder builder = new WikiStoryworldBuilder();
        builder.setCreator(player);
        builder.setPubliclyModifiable(true);
        builder.setVisible(true);
        sendMessage("What would you like the title of your storyworld to be?");
        builder.setTitle(getCommand());
        sendMessage("What would you like the description of your storyworld to be?");
        builder.setDescription(getCommand());
        sendMessage("What would you like the introduction to the storyworld to be? (users will see this only when they begin)");
        builder.setEntryText(getCommand());
        sendMessage("What would you like the starting location's text to be? (users will see this every time they return to this location)");
        builder.setEntryBuilder(new ImmutableLocationBuilder().setCreator(player).setDescription(getCommand()));
        Storyworld      storyworld = storyworldService.create(builder, false);
        return storyworld;
    }

    public void visit(Create create) throws NotFoundException, UncreateableException {
        if (!create.isCreateable()) {
            return;
        }

        switch (create.getCreateableType()) {
            case LOCATION:
                sendMessage("Would you like the link to the location to require the user to have an item?");
                boolean blockable = ConfirmationParser.parse(getCommand());
                LinkBuilder linkBuilder = new DirectionalLinkBuilder();;
                if (blockable) {
                    linkBuilder = new BlockableLinkBuilder();
                }
                if (linkBuilder instanceof BlockableLinkBuilder) {
                    sendMessage("What would you like the required item to be?");
                    List<Item> items  = new ArrayList<>(player.getCurrentProgress().getItems());
                    Item       choice = items.get(selectIndex(items, "item"));
                    ((BlockableLinkBuilder) linkBuilder).setRequiredItem(choice);
                    sendMessage("What would you like the text to be if the user does not have the required item (and is blocked from passing through the link)?");
                    ((BlockableLinkBuilder) linkBuilder).setFailText(getCommand());
                }
                linkBuilder.setCreator(player);
                sendMessage("What would you like the text describing the link to the location to say? \n" +
                        "It should complete this sentence: " + create.getDirection().formatted() + " there is...");
                linkBuilder.setDescription(getCommand());
                sendMessage("What would you like the text of the link to be while the user moves to the new location?");
                linkBuilder.setPassText(getCommand());
                sendMessage("What would you like the text of the location to be once the user arrives?");

                ImmutableLocationBuilder locationBuilder = new ImmutableLocationBuilder();
                locationBuilder.setCreator(player);
                locationBuilder.setDescription(getCommand())
                    .setStoryworld(player.getCurrentProgress().getStoryworld());

                Location location = null;
                Link link = null;

                try {
                    location = locationService.create(locationBuilder);
                    linkBuilder.setToLocation(location);
                    link = linkService.create(linkBuilder);
                    player.getCurrentProgress().getLocation().addOutboundLink(create.getDirection(), link);
                    locationService.createOrUpdate(player.getCurrentProgress().getLocation());
                } catch (UncreateableException | NullPointerException e) {
                    sendMessage(e.getMessage());
                    if (location != null) {
                        // XXX not implemented
//                        sendMessage("rolling back location create");
//                        locationService.delete(location);
                    }
                    if (link != null) {
                        sendMessage("rolling back link create");
                        player.getCurrentProgress().getLocation().getOutboundLinks().remove(create.getDirection(), location);
                        locationService.createOrUpdate(player.getCurrentProgress().getLocation());
                    }
                }
                break;
            case LINK:
                DirectionalLinkBuilder directionalLinkBuilder = new DirectionalLinkBuilder();
                directionalLinkBuilder.setCreator(player);
                sendMessage("What would you like the text describing the link to say? \n" +
                        "It should complete this sentence: " + create.getDirection().formatted() + " there is...");
                directionalLinkBuilder.setDescription(getCommand());
                sendMessage("What would you like the text of the link to be while the user moves to the new location?");
                directionalLinkBuilder.setPassText(getCommand());
                sendMessage("Which location would you like to connect the link to?");
                int i = 1;
                List<Location> locationHistory = new ArrayList<>(create.getMessage().getPlayer().getCurrentProgress().getVisitedLocations());
                for (Location loc: locationHistory) {
                    if (loc.isActive()) {
                        String[] text = loc.getDescription().split("\n");
                        sendMessage("previous Location " + i + ": " + StringUtils.abbreviate(text[0], 30));
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
                    Link createdLink = linkService.create(directionalLinkBuilder);
                    player.getCurrentProgress().getLocation().addOutboundLink(create.getDirection(), createdLink);
                    locationService.createOrUpdate(player.getCurrentProgress().getLocation());
                } catch (UncreateableException e) {
                    sendMessage(e.getMessage());
                }
                break;
            case ITEM:
                UsableItemBuilder itemBuilder = new UsableItemBuilder();
                itemBuilder.setCreator(player);
                sendMessage("What would you like the one-word name of the item to be?");
                itemBuilder.setName(getCommand());
                sendMessage("What would you like the description of the item to be?");
                itemBuilder.setDescription(getCommand());
                sendMessage("What would you like the description of using the item to be?");
                itemBuilder.setUseText(getCommand());
                try {
                    Item item = itemService.create(itemBuilder.build());
                    player.getCurrentProgress().getLocation().addItem(item);
                    locationService.createOrUpdate(player.getCurrentProgress().getLocation());
                } catch (UncreateableException e) {
                    sendMessage(e.getMessage());
                }
                break;

            case STORYWORLD:
                createNewStoryworld();
                break;
            default:

                break;
        }
    }

    public void visit(Delete delete) {
        sendMessage("Are you sure you want to delete " + delete.getCreateableType() + " " + delete.getDirection() + "?");
        if (ConfirmationParser.parse(getCommand())) {
            if (CreateableType.LOCATION.equals(delete.getCreateableType())) {
                // XXX not implemented
//                locationService.delete(delete);
            }
        }
    }

    public void visit(Edit edit) {
        if (!edit.isCreateable()) {
            return;
        }
        edit.getMessage().resetText();
        switch(edit.getCreateableType()) {
            case LOCATION:
                ImmutableLocationBuilder locationBuilder = new ImmutableLocationBuilder();
                locationBuilder.setCreator(player);
                sendMessage("What would you like the text of the location to be once the user arrives?");
                locationBuilder.setDescription(getCommand());
                Location formerLocation = edit.getMessage()
                                              .getPlayer()
                                              .getCurrentProgress()
                                              .getLocation();
                locationBuilder.setPreviousLocation(formerLocation);
                locationBuilder.setStoryworld(edit.getMessage()
                                                  .getPlayer()
                                                  .getCurrentProgress()
                                                  .getStoryworld());
                try {

                    Location location = locationService.create(locationBuilder);
                    cloneLinks(location, formerLocation);
                    locationService.create(location);

                    // retire formerLocation
                    formerLocation.setActive(false);
                    formerLocation.setForwardingLocation(location);
                    locationService.createOrUpdate(formerLocation);

                    player.getCurrentProgress()
                          .setLocation(location);
                    playerService.create(player);
                } catch (UncreateableException | NotFoundException e) {
                    sendMessage(e.getMessage());
                }
                break;
            case LINK:
                DirectionalLinkBuilder linkBuilder = new DirectionalLinkBuilder();
                linkBuilder.setCreator(player);
                sendMessage("What would you like the text describing the link to the location to say? \n" +
                        "It should complete this sentence: " + edit.getDirection()
                                                                   .formatted() + " there is...");
                linkBuilder.setDescription(getCommand());
                sendMessage("What would you like the text of the link to be while the user moves to the new location?");
                linkBuilder.setPassText(getCommand());
                linkBuilder.setToLocation(player.getCurrentProgress()
                                                .getLocation()
                                                .getOutboundLink(edit.getDirection())
                                                .getToLocation());
                try {
                    Link link = linkService.create(linkBuilder);
                    edit.getMessage()
                        .getPlayer()
                        .getCurrentProgress()
                        .getLocation()
                        .addOutboundLink(edit.getDirection(), link);
                    locationService.createOrUpdate(edit.getMessage()
                                                       .getPlayer()
                                                       .getCurrentProgress()
                                                       .getLocation());
                } catch (UncreateableException | NotFoundException e) {
                    sendMessage(e.getMessage());
                }
                break;
            case ITEM:
                break;
            case STORYWORLD:
                break;
            default:
                sendMessage("invalid creatableType");
                break;
        }
    }

    public void visit(Help help) {

    }

    public void visit(Move move) {
        playerService.create(player);
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
