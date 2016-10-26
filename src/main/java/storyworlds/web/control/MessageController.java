package storyworlds.web.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import storyworlds.model.implementation.IdentifiedPlayer;

@Controller
public class MessageController {

    private Logger logr = LoggerFactory.getLogger(getClass());

    @RequestMapping("/")
    @ResponseBody
    public IdentifiedPlayer getMessage() {
        logr.debug("called get message");
        return new IdentifiedPlayer("foo", "bar", "baz");
    }

}
