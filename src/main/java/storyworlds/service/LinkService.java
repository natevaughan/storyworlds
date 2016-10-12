package storyworlds.service;

import storyworlds.action.Create;
import storyworlds.action.Edit;
import storyworlds.create.properties.DirectionalLinkProperties;
import storyworlds.create.properties.Validateable;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Link;
import storyworlds.model.implementation.DirectionalLink;


public class LinkService {
    public Link create(Create create) throws UncreateableException {
        Validateable properties = create.getProperties();
        if (properties.isValid() && properties instanceof DirectionalLinkProperties) {
            Link link = new DirectionalLink(((DirectionalLinkProperties) properties).getDescription(),
                    ((DirectionalLinkProperties) properties).getToLocation(),
                    create.getMessage().getPlayer().getLocation(), create.getDirection(),
                    ((DirectionalLinkProperties) properties).getPassText());
            create.getMessage().getPlayer().getLocation().addOutboundLink(link);
            link.bind();
            create.setSuccessful(true);
            return link;
        }
        throw new UncreateableException("Unable to create link");
    }

    public Link edit(Edit edit) throws UncreateableException {
        Validateable properties = edit.getProperties();
        if (properties.isValid() && properties instanceof DirectionalLinkProperties) {
            Link link = new DirectionalLink(((DirectionalLinkProperties) properties).getDescription(),
                    ((DirectionalLinkProperties) properties).getToLocation(),
                    edit.getMessage().getPlayer().getLocation(), edit.getDirection(),
                    ((DirectionalLinkProperties) properties).getPassText());
            edit.getMessage().getPlayer().getLocation().addOutboundLink(link);
            link.bind();
            edit.setSuccessful(true);
            return link;
        }
        throw new UncreateableException("Unable to edit link");
    }
}
