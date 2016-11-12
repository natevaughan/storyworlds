package storyworlds.web.control;

import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import storyworlds.exception.NotFoundException;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Storyworld;
import storyworlds.model.builder.StoryworldBuilder;
import storyworlds.service.StoryworldService;

/**
 * Created by nvaughan on 10/29/2016.
 */
@Controller
@RequestMapping(value = "/storyworld")
public class StoryworldController {

    private Logger logr = LoggerFactory.getLogger(getClass());

    @Autowired
    StoryworldService storyworldService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    public Collection<Storyworld> list() {
//        UserDetailsImpl  principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        logr.info(principal.getPlayer().toString());
        return storyworldService.list();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public Storyworld get(@PathVariable String id) throws NotFoundException {
        return storyworldService.get(id);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Storyworld create(@RequestBody StoryworldBuilder storyworld) throws UncreateableException {
        return storyworldService.create(storyworld);
    }
}
