package storyworlds.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import storyworlds.action.parser.DirectionParser;
import storyworlds.exception.BadRequestException;
import storyworlds.exception.InvalidDirectionException;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Direction;
import storyworlds.model.Location;
import storyworlds.model.Player;
import storyworlds.model.builder.LinkBuilder;
import storyworlds.model.builder.LocationBuilder;
import storyworlds.model.implementation.IdentifiedPlayer;
import storyworlds.model.implementation.UserDetailsImpl;
import storyworlds.service.LocationService;
import storyworlds.service.PlayerService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nvaughan on 10/27/2016.
 */
@Controller
@RequestMapping(value = "/location")
public class LocationController extends AbstractController {

    @Autowired
    LocationService locationService;

    @Autowired
    PlayerService playerService;

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    @ResponseBody
    public Location getCurrentLocation(HttpServletResponse response) throws BadRequestException {
        Object o =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (o instanceof UserDetailsImpl) {
            Player player = ((UserDetailsImpl) o).getPlayer();
            return playerService.get(player).getCurrentProgress().getLocation();
        } else {
            throw new BadRequestException("No current player is set");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Location getLocation(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        Location location = locationService.get(id);
        if (location == null) {
            response.sendError(HttpStatus.NOT_FOUND.value(), "Not found: " + id);
            return null;
        }

        return location;
    }
//
//    @RequestMapping(value = "/template")
//    @ResponseBody
//    public ImmutableLocation.Builder getTemplate() {
//        ImmutableLocation.Builder builder = new ImmutableLocation.Builder();
//        builder.setDescription("A description that will be shown when the player arrives");
//        return builder;
//    }

    @RequestMapping(value = "/{id}/create/{direction}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@PathVariable("id") String id, @PathVariable("direction") String direction, @RequestBody LinkBuilder builder, HttpServletResponse response) throws UncreateableException, InvalidDirectionException {
        Direction dir = DirectionParser.parse(direction);
        IdentifiedPlayer player = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPlayer();
        builder.setCreator(player);
        return locationService.create(builder, dir);
    }


    @RequestMapping(value = "/create/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Location createLocation(@RequestBody LocationBuilder builder, HttpServletResponse response) throws UncreateableException, InvalidDirectionException {
        builder.setCreator(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPlayer());
        return locationService.create(builder);
    }
}
