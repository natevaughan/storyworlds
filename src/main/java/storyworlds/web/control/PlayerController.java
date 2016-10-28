package storyworlds.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import storyworlds.model.Player;
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

}
