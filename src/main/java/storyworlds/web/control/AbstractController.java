package storyworlds.web.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import storyworlds.exception.BadRequestException;
import storyworlds.exception.InvalidLinkException;
import storyworlds.exception.NotFoundException;
import storyworlds.exception.UnauthorizedException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nvaughan on 10/30/2016.
 */
public class AbstractController {
    protected Logger logr = LoggerFactory.getLogger(getClass());


    @ExceptionHandler({InvalidLinkException.class, BadRequestException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    String handleBadLink(Exception e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return null;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    String handleNotFound(Exception e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return null;
    }


    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public @ResponseBody
    String handleUnauthorized(Exception e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        return null;
    }

}
