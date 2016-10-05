package storyworlds.service;


import storyworlds.action.Create;
import storyworlds.action.Edit;
import storyworlds.constants.PropertyKeys;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.enumeration.Links;
import storyworlds.model.enumeration.Locations;
import storyworlds.model.implementation.DirectionalLink;
import storyworlds.model.implementation.ImmutableLocation;

public class LocationService implements PropertyKeys {

    public void build(Create create) {
        // XXX: todo
        if (Locations.IMMUTABLE.equals(create.getLocationType())) {
            if (create.getMessage().getFields().containsKey(KEY_LOCATION_TEXT)) {
                Location location = new ImmutableLocation(create.getMessage().getFields().get(KEY_LOCATION_TEXT));
                if (Links.DIRECTIONAL.equals(create.getLinkType())) {
                    if (create.getMessage().getFields().containsKey(KEY_LINK_PASS_TEXT) && create.getMessage().getFields().containsKey(KEY_LINK_DESCRIPTION)) {
                        Link link = new DirectionalLink(create.getMessage().getFields().get(KEY_LINK_DESCRIPTION), location, create.getMessage().getPlayer().getLocation(), create.getDirection(), create.getMessage().getFields().get(KEY_LINK_PASS_TEXT));
                        create.getMessage().getPlayer().getLocation().addLink(link);
                        create.setSuccessful(true);
                    }
                }
            }
        }
    }

    public void edit(Edit edit) {
        if (edit.getMessage().getFields().containsKey(KEY_LOCATION_TEXT)) {
            Location location = new ImmutableLocation(edit.getMessage().getFields().get(KEY_LOCATION_TEXT));
            for (Link l : edit.getMessage().getPlayer().getLocation().getLinks().values()) {
                location.addLink(l);
            }
        }
    }
}

