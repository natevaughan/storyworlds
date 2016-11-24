package storyworlds.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storyworlds.action.Action;
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
import storyworlds.action.parser.Article;
import storyworlds.action.visitor.ActionVisitor;
import storyworlds.create.CreateableType;
import storyworlds.exception.InvalidLinkException;
import storyworlds.exception.NotFoundException;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Direction;
import storyworlds.model.Item;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.service.message.Message;

/**
 * Responsibilities
 * -Validating action
 * -Executing action
 * -Setting action success
 * -Setting message text
 */
@Service
public class ActionDoVisitorService implements ActionVisitor {

    @Autowired
    PlayerService playerService;

    @Autowired
    LocationService locationService;

    @Autowired
    ItemService itemService;

    public void visit(Create create) throws UncreateableException {
        StringBuilder sb = new StringBuilder();
        if (create.isCreateable()) {
            sb.append("OK, creating ").append(create.getCreateableType());
            if (!CreateableType.ITEM.equals(create.getCreateableType())) {
                sb.append(" ").append(create.getDirection());
            }
            create.getMessage().addLine(sb.toString());
            create.setSuccessful(true);
        } else {
            sb.append("Unable to create ").append(create.getCreateableType()).append("\n");

            if (create.getCreateableType() == null) {
                sb.append(enumerateCreatables("create"));
            }
            if (create.getDirection() == null) {
                sb.append(enumerateDirections());
            }
            if (create.getMessage().getPlayer().getCurrentProgress().getLocation().getOutboundLink(create.getDirection()) != null) {
                sb.append(create.getCreateableType()).append(" already exists ").append(create.getDirection().formatted().toLowerCase());
            }
            throw new UncreateableException(sb.toString());
        }
    }

    public void visit(Edit edit) throws UncreateableException {
        StringBuilder sb = new StringBuilder();
        if (edit.isCreateable()) {
            sb.append("OK, editing ");
            if (edit.getDirection() != null) {
                sb.append(edit.getCreateableType() + " " + edit.getDirection());
            } else {
                sb.append("this " + edit.getCreateableType());
            }
            edit.setSuccessful(true);
        } else {
            if (CreateableType.LOCATION.equals(edit.getCreateableType()) && edit.getDirection() != null) {
                throw new UncreateableException("To edit a location, first travel to it.");
            }
        }
        edit.getMessage().addLine(sb.toString());
    }

    @Override
    public void visit(Delete delete) throws Exception {

    }

    public void visit(Help help) {
        help.getMessage().addLine("Valid actions: ");
        for (Action action : Action.values()) {
            help.getMessage().addLine(action.getExplanation());
        }
        help.setSuccessful(true);
    }

    public void visit(Status status) {
        describeLocation(status.getMessage());
        if (status.getMessage().getPlayer().getCurrentProgress().getItems().isEmpty()) {
            status.getMessage().addLine("You have no items");
        } else {
            status.getMessage().addLine("Your inventory: ");
            for (Item item : status.getMessage().getPlayer().getCurrentProgress().getItems()) {
                status.getMessage().addLine(item.getName());
            }
        }
        status.setSuccessful(true);
    }

    public void visit(Map map) {
        map.getMessage().addLine("Map feature not yet supported");
    }

    @Override
    public void visit(Quit quit) {

    }

    public void visit(Move move) throws InvalidLinkException {

        Link link = move.getMessage().getPlayer().getCurrentProgress().getLocation().getOutboundLink(move.getDirection());

        if (link == null) {
            throw new InvalidLinkException("Nothing " + move.getDirection().formatted());
        }

        if (link.getToLocation() == null) {
            throw new InvalidLinkException("Bad link: no destination location.");
        }

        if (!link.isPassable(move.getMessage().getPlayer())) {
            throw new InvalidLinkException(link.getPassText(move.getMessage().getPlayer()));
        }

        try {
            Location toLocation = locationService.get(link.getToLocation().getForwardingLocation());
        } catch (NotFoundException e) {
            throw new InvalidLinkException(e);
        }

        move.getMessage().addLine(link.getPassText(move.getMessage().getPlayer()));

        move.setSuccessful(true);

        move.getMessage().getPlayer().getCurrentProgress().setLocation(move.getMessage().getPlayer().getCurrentProgress().getLocation().getOutboundLink(move.getDirection()).getToLocation());
        describeLocation(move.getMessage());
    }

    public void visit(Take take) throws NotFoundException {
        if (take.getMessage().getPlayer().getCurrentProgress().getLocation().getItem(take.getItemName()) == null) {
            throw new NotFoundException("Item not found: " + take.getItemName());
        }
        take.setSuccessful(true);
        take.getMessage().getPlayer().getCurrentProgress().addItem(take.getMessage().getPlayer().getCurrentProgress().getLocation().getItem(take.getItemName()));
        playerService.createOrUpdate(take.getMessage().getPlayer());
        take.getMessage().addLine("You take the " + take.getItemName());
    }

    public void visit(Use use) {
        Collection<Item> items = use.getMessage().getPlayer().getCurrentProgress().getItems();

        if (use.getItemName() == null) {
            use.getMessage().addLine("You must specify an item to use.");
            return;
        }

        for (Item i : items) {
            if (i.getName().equalsIgnoreCase(use.getItemName())) {
                use.getMessage().getPlayer().getCurrentProgress().setActiveItem(i);
                use.setSuccessful(true);
                use.getMessage().addLine(i.getUseText());
                return;
            }
        }
        use.getMessage().addLine("You don't have " + Article.get(use.getItemName()) + " " + use.getItemName());
    }

    private void describeLocation(Message m) {
        m.addLine(m.getPlayer().getCurrentProgress().getLocation().getDescription());
        java.util.Map<Direction, Link> links = m.getPlayer().getCurrentProgress().getLocation().getOutboundLinks();
        for (Direction direction : links.keySet()) {
            m.addLine(direction.formatted() + " is " + links.get(direction).getDescription());
        }
        for (Item item : m.getPlayer().getCurrentProgress().getLocation().getItems()) {
            if (!m.getPlayer().getCurrentProgress().getItems().contains(item)) {
                m.addLine("There is a " + item.getName() + " here");
            }
        }
    }

    private String enumerateDirections() {
        StringBuilder sb = new StringBuilder();
        sb.append("Please specify a direction: ");
        int i = 0;
        for (Direction direction : Direction.values()) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(direction);
            ++i;
        }
        return sb.toString();
    }

    private String enumerateCreatables(String type) {
        StringBuilder sb = new StringBuilder();
        sb.append("Please specify something to ").append(type).append(": ");
        int i = 0;
        for (CreateableType createables : CreateableType.values()) {
            if (i > 0)
                sb.append(", ");
            sb.append(createables);
            ++i;
        }
        return sb.toString();
    }
}
