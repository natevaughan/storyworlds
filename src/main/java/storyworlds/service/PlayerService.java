package storyworlds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Location;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import storyworlds.exception.BadLinkException;
import storyworlds.model.Link;
import storyworlds.model.Player;
import storyworlds.model.enumeration.Direction;
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

    @PostConstruct
    private void init() {
        cache = new LRUCache<>(Integer.parseInt(env.getProperty(KEY_PLAYER_CACHE_SIZE)));
        repo = playerRepo;
    }

    public Player move(String id, Direction direction) throws BadLinkException {
        Player player = get(id);
        storyworlds.model.Location location = locationService.get(player.getLocation().getId());
        Link link = location.getOutboundLink(direction);
        if (link == null) {
            throw new BadLinkException("No link " + direction.formatted());
        }
        if (!link.isPassable(player)) {
            throw new BadLinkException(link.getPassText(player));
        } else {
            player.setLocation(link.getToLocation());
            return playerRepo.save(player);
        }
    }
}
