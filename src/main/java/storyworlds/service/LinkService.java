package storyworlds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storyworlds.action.Create;
import storyworlds.action.Edit;
import storyworlds.create.properties.DirectionalLinkProperties;
import storyworlds.create.properties.Validateable;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Link;
import storyworlds.model.implementation.DirectionalLink;
import storyworlds.model.implementation.persistence.LocationRepository;

@Service
public class LinkService {

    @Autowired
    LocationRepository locationRepository;

    public Link create(Create create) throws UncreateableException {
        Validateable properties = create.getProperties();
        if (properties.isValid() && properties instanceof DirectionalLinkProperties) {
            Link link = new DirectionalLink(((DirectionalLinkProperties) properties).getDescription(),
                    ((DirectionalLinkProperties) properties).getToLocation(),
                    create.getMessage().getPlayer().getLocation(), create.getDirection(),
                    ((DirectionalLinkProperties) properties).getPassText());
            create.getMessage().getPlayer().getLocation().addOutboundLink(link);
            link.bind();
            locationRepository.save(link.getFromLocation());
            locationRepository.save(link.getToLocation());
            create.setSuccessful(true);
            return link;
        }
        throw new UncreateableException("Unable to create link");
    }

    public Link edit(Edit edit) throws UncreateableException {
        Validateable properties = edit.getProperties();
        if (properties != null && properties.isValid() && properties instanceof DirectionalLinkProperties) {
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
