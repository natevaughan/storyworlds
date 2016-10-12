package storyworlds.service;

import storyworlds.action.Create;
import storyworlds.action.Edit;
import storyworlds.create.properties.DirectionalLinkProperties;
import storyworlds.create.properties.Validateable;
import storyworlds.model.Link;
import storyworlds.model.implementation.DirectionalLink;


public class LinkService {
    public void create(Create create) {
        Validateable properties = create.getProperties();
        if (properties.isValid()) {
            if (properties instanceof DirectionalLinkProperties) {
                Link link = new DirectionalLink(((DirectionalLinkProperties) properties).getDescription(),
                        ((DirectionalLinkProperties) properties).getToLocation(),
                        create.getMessage().getPlayer().getLocation(), create.getDirection(),
                        ((DirectionalLinkProperties) properties).getPassText());
                create.getMessage().getPlayer().getLocation().addOutboundLink(link);
                link.bind();
                create.setSuccessful(true);
            }
        }
    }

    public void edit(Edit edit) {
        Validateable properties = edit.getProperties();
        if (properties.isValid()) {
            if (properties instanceof DirectionalLinkProperties) {
                Link link = new DirectionalLink(((DirectionalLinkProperties) properties).getDescription(),
                        ((DirectionalLinkProperties) properties).getToLocation(),
                        edit.getMessage().getPlayer().getLocation(), edit.getDirection(),
                        ((DirectionalLinkProperties) properties).getPassText());
                edit.getMessage().getPlayer().getLocation().addOutboundLink(link);
                link.bind();
                edit.setSuccessful(true);
            }
        }
    }
}
