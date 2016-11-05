package storyworlds.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import storyworlds.action.parser.DirectionParser;
import storyworlds.exception.BadLinkException;
import storyworlds.model.Player;
import storyworlds.model.Direction;
import storyworlds.service.PlayerService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nvaughan on 10/28/2016.
 */
@Controller
@RequestMapping(value = "/player")
public class PlayerController extends AbstractController {

    @Autowired
    PlayerService playerService;

    @RequestMapping("/{id}")
    @ResponseBody
    public Player getById(@PathVariable String id, HttpServletResponse response) {
        return playerService.get(id);
    }


    @RequestMapping("/{id}/move/{direction}")
    @ResponseBody
    public Player move(@PathVariable String id, @PathVariable String direction, HttpServletResponse response) throws IOException, BadLinkException {
        Direction dir = DirectionParser.parse(direction);
        if (Direction.ERROR.equals(dir)) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid direction: " + direction);
            return null;
        }
        return playerService.move(id, dir);
    }

    @ExceptionHandler(BadLinkException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleException(Exception e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return null;
    }
}
