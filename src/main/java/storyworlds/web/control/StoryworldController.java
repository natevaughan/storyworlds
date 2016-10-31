package storyworlds.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import storyworlds.model.Storyworld;
import storyworlds.service.StoryworldService;

import java.util.Collection;

/**
 * Created by nvaughan on 10/29/2016.
 */
@Controller
@RequestMapping(value = "/storyworld")
public class StoryworldController {

    @Autowired
    StoryworldService storyworldService;

    @RequestMapping(value = "/")
    @ResponseBody
    public Collection<Storyworld> list() {
        return storyworldService.list();
    }

    @RequestMapping(value = "/{id}")
    @ResponseBody
    public Storyworld get(@PathVariable String id) {
        return storyworldService.get(id);
    }
}
