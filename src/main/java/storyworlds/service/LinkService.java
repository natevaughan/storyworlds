package storyworlds.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Link;
import storyworlds.model.builder.LinkBuilder;

@Service
public class LinkService {

    private Logger logr = LoggerFactory.getLogger(getClass());

    public Link create(LinkBuilder linkBuilder) throws UncreateableException {
        Link link = linkBuilder.build();

        logr.debug("Link created: " + link.getClass().getTypeName() + " by " + link.getCreator().getUsername() + " in " + link.getCreator().getCurrentProgress().getStoryworld().getTitle());

        return link;
    }
}
