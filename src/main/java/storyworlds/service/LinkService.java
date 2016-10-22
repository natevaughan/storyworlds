package storyworlds.service;

import org.springframework.stereotype.Service;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Link;
import storyworlds.model.LinkBuilder;

@Service
public class LinkService {
    public Link create(LinkBuilder linkBuilder) throws UncreateableException {
        Link link = linkBuilder.build();
        return link;
    }
}
