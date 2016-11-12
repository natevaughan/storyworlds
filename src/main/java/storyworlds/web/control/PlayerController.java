package storyworlds.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import storyworlds.action.Actionable;
import storyworlds.exception.InvalidDirectionException;
import storyworlds.exception.InvalidLinkException;
import storyworlds.exception.UnauthorizedException;
import storyworlds.exception.UncreateableException;
import storyworlds.exception.UnrecognizedInputException;
import storyworlds.model.Player;
import storyworlds.model.builder.PlayerBuilder;
import storyworlds.model.implementation.AnonymousPlayer;
import storyworlds.model.implementation.IdentifiedPlayer;
import storyworlds.model.implementation.UserDetailsImpl;
import storyworlds.service.AnonymousPlayerService;
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
    AnonymousPlayerService anonymousPlayerService;

    @Autowired
    MessageService messageService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    public Object current(HttpServletResponse response) {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public Player getById(@PathVariable String id, HttpServletResponse response) {
        return playerService.get(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @ResponseBody
    public Player update(@RequestBody IdentifiedPlayer player, @PathVariable String id, HttpServletResponse response) throws Exception {
        IdentifiedPlayer sessionPlayer = (IdentifiedPlayer) playerService.get(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPlayer());
        if (!player.getId().equals(id) || !sessionPlayer.getId().equals(player.getId())) {
            throw new UnauthorizedException("ID mismatch among logged in player, player object, and path ID variable");
        }
        return playerService.update(player.build());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Player create(@RequestBody PlayerBuilder playerBuilder, HttpServletResponse response) throws UncreateableException {
        return playerService.create(playerBuilder.build());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create/anonymous", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Player createAnonymous(HttpServletResponse response) throws UncreateableException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Player player = anonymousPlayerService.cache(new AnonymousPlayer());

//        Authentication authentication = new AnonymousAuthenticationToken(player.getId(), player, player.getGrantedAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return player;

    }

    @RequestMapping(method = RequestMethod.POST, value = "/action", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Actionable action(@RequestBody Message message, HttpServletResponse response) throws IOException, InvalidLinkException, UnrecognizedInputException, InvalidDirectionException {
        Player player = playerService.get(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPlayer());
        message.setPlayer(player);
        return messageService.process(message);
    }

}
