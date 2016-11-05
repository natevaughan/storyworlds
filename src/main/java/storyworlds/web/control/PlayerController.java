package storyworlds.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import storyworlds.action.Actionable;
import storyworlds.exception.InvalidDirectionException;
import storyworlds.exception.InvalidLinkException;
import storyworlds.exception.UnrecognizedInputException;
import storyworlds.model.Player;
import storyworlds.model.implementation.UserDetailsImpl;
import storyworlds.service.PlayerService;
import storyworlds.service.message.Message;
import storyworlds.service.message.MessageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by nvaughan on 10/28/2016.
 */
@Controller
@RequestMapping(value = "/player")
public class PlayerController extends AbstractController {

    @Autowired
    PlayerService playerService;

    @Autowired
    MessageService messageService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    public Collection<Player> list(HttpServletResponse response) {
        return playerService.list();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public Player getById(@PathVariable String id, HttpServletResponse response) {
        return playerService.get(id);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/{id}/action", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Actionable action(@PathVariable String id, @RequestBody Message message, HttpServletResponse response) throws IOException, InvalidLinkException, UnrecognizedInputException, InvalidDirectionException {
        Player player = playerService.get(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPlayer());
        message.setPlayer(player);
        return messageService.process(message);
    }

    @ExceptionHandler(InvalidLinkException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleException(Exception e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return null;
    }
}
