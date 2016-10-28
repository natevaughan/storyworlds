package storyworlds.web.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import storyworlds.model.implementation.IdentifiedPlayer;
import storyworlds.service.GraphCacheService;
import storyworlds.service.message.Message;
import storyworlds.service.message.MessageService;

@Controller
public class MessageController {

//    @Autowired
//    MessageService messageService;

    private Logger logr = LoggerFactory.getLogger(getClass());

    @RequestMapping("/")
    @ResponseBody
    public String getMessage(Message message) {
//        return messageService.process(message);
        return "foo";
    }


}
