package storyworlds.service;

import storyworlds.action.Create;
import storyworlds.action.Edit;
import storyworlds.constants.PropertyKeys;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.enumeration.Links;
import storyworlds.model.implementation.DirectionalLink;

import java.util.ArrayList;
import java.util.List;


public class LinkService implements PropertyKeys {
    public void create(Create create) {
        // XXX: todo
        if (create.getLocationIndex() != null) {
            List<Location> historyList = new ArrayList<>(create.getMessage().getPlayer().getLocationHistory());
            Location location = historyList.get(create.getLocationIndex());
            if (Links.DIRECTIONAL.equals(create.getLinkType())) {
                if (create.getMessage().getFields().containsKey(KEY_LINK_PASS_TEXT) && create.getMessage().getFields().containsKey(KEY_LINK_DESCRIPTION)) {
                    Link link = new DirectionalLink(create.getMessage().getFields().get(KEY_LINK_DESCRIPTION), location, create.getMessage().getPlayer().getLocation(), create.getDirection(), create.getMessage().getFields().get(KEY_LINK_PASS_TEXT));
                    create.getMessage().getPlayer().getLocation().addLink(link);
                    create.setSuccessful(true);
                }
            }
        }
    }

    public void edit(Edit edit) {

    }
}
