package storyworlds.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import storyworlds.model.Player;
import storyworlds.model.implementation.IdentifiedPlayer;
import storyworlds.model.implementation.UserDetailsImpl;
import storyworlds.model.implementation.persistence.PlayerRepository;

/**
 * Created by nvaughan on 11/5/2016.
 */
@Service
public class MongoUserDetailsService implements UserDetailsService {

    @Autowired
    PlayerRepository playerRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Player player = playerRepository.findByUsername(username);
        if (player == null || !(player instanceof IdentifiedPlayer)) {
            throw new UsernameNotFoundException("Not found: " + username);
        } else {
            return new UserDetailsImpl((IdentifiedPlayer) player);
        }
    }
}
