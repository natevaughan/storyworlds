package storyworlds.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import storyworlds.action.parser.DirectionParser;
import storyworlds.model.Player;
import storyworlds.model.enumeration.Direction;
import storyworlds.service.LocationService;
import storyworlds.service.PlayerService;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by nvaughan on 10/28/2016.
 */
@Controller
@RequestMapping(value = "/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @RequestMapping("/{id}")
    @ResponseBody
    public Player getById(@PathVariable String id, HttpServletResponse response) {
        return playerService.get(id);
    }


    @RequestMapping("/{id}/move/{direction}")
    @ResponseBody
    public Player move(@PathVariable String id, @PathVariable String direction, HttpServletResponse response) {
        Direction dir = DirectionParser.parse(direction);
        if (Direction.ERROR.equals(direction)) {
            // XXX: throw exception?
            return null;
        }
        return playerService.move(id, dir);
    }

}
