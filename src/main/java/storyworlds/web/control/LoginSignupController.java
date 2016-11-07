package storyworlds.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Player;
import storyworlds.model.builder.PlayerBuilder;
import storyworlds.model.implementation.IdentifiedPlayer;
import storyworlds.service.PlayerService;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by nvaughan on 11/5/2016.
 */
@Controller
public class LoginSignupController extends AbstractController {

    @Autowired
    PlayerService playerService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Player create(@RequestBody PlayerBuilder builder, HttpServletResponse response) throws UncreateableException {
        Player player = builder.build();
        GrantedAuthority userAuthority = new SimpleGrantedAuthority("ROLE_USER");
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(userAuthority);
        player.setGrantedAuthorities(grantedAuthorities);
        Player identifiedPlayer = playerService.create(player);
        Authentication authentication = new UsernamePasswordAuthenticationToken(identifiedPlayer, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return identifiedPlayer;
    }

}
