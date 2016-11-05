package storyworlds.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Location;
import storyworlds.model.implementation.ImmutableLocation;
import storyworlds.service.LocationService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nvaughan on 10/27/2016.
 */
@Controller
@RequestMapping(value = "/location")
public class LocationController {

    @Autowired
    LocationService locationService;

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

    @RequestMapping(value = "/template")
    @ResponseBody
    public ImmutableLocation.Builder getTemplate() {
        ImmutableLocation.Builder builder = new ImmutableLocation.Builder();
        builder.setDescription("A description that will be shown when the player arrives");
        return builder;
    }

    @RequestMapping(value = "/{id}/location/{direction}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@PathVariable("id") String id, @RequestBody ImmutableLocation.Builder builder, HttpServletResponse response) throws UncreateableException {

        return locationService.create(builder);
    }

}
