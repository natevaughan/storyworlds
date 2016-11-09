package storyworlds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import storyworlds.exception.InvalidLinkException;
import storyworlds.model.Link;
import storyworlds.model.Player;
import storyworlds.model.Direction;
import storyworlds.model.implementation.AnonymousPlayer;
import storyworlds.model.implementation.IdentifiedPlayer;
import storyworlds.model.implementation.persistence.PlayerRepository;

import javax.annotation.PostConstruct;

/**
 * Created by nvaughan on 10/28/2016.
 */
@Service
public class PlayerService extends AbstractCachingService<Player> {

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    LocationService locationService;

    @Autowired
    private Environment env;

    private BCryptPasswordEncoder encoder;

    @PostConstruct
    private void init() {
        lruCache = new LRUCache<>(Integer.parseInt(env.getProperty(KEY_PLAYER_CACHE_SIZE)));
        repo = playerRepo;
        encoder = new BCryptPasswordEncoder();
    }

    public Player get(Player player) {
        return get(player.getId());
    }

    public Player move(String id, Direction direction) throws InvalidLinkException {
        Player player = get(id);
        storyworlds.model.Location location = locationService.get(player.getCurrentProgress().getLocation().getId());
        Link link = location.getOutboundLink(direction);
        if (link == null) {
            throw new InvalidLinkException("No link " + direction.formatted());
        }
        if (!link.isPassable(player)) {
            throw new InvalidLinkException(link.getPassText(player));
        } else {
            player.getCurrentProgress().setLocation(link.getToLocation());
            return playerRepo.save(player);
        }
    }

    @Override
    public Player create(Player player) {
        if (player instanceof IdentifiedPlayer) {
            ((IdentifiedPlayer) player).setPassword(encoder.encode(((IdentifiedPlayer) player).getPassword()));
        }
        return super.create(player);

    }

    public Player getByUsername(String username) {
        return playerRepo.findByUsername(username);
    }

    public Player getByEmail(String email) {
        return playerRepo.findByEmail(email);

    }
}
