package storyworlds.service;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import storyworlds.exception.InvalidLinkException;
import storyworlds.exception.NotFoundException;
import storyworlds.model.Direction;
import storyworlds.model.Link;
import storyworlds.model.Player;
import storyworlds.model.Progress;
import storyworlds.model.Storyworld;
import storyworlds.model.implementation.IdentifiedPlayer;
import storyworlds.model.implementation.StoryworldProgress;
import storyworlds.model.implementation.persistence.PlayerRepository;

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
    StoryworldService storyworldService;

    @Autowired
    private Environment env;

    private BCryptPasswordEncoder encoder;

    @PostConstruct
    private void init() {
        lruCache = new LRUCache<>(Integer.parseInt(env.getProperty(KEY_PLAYER_CACHE_SIZE)));
        repo = playerRepo;
        encoder = new BCryptPasswordEncoder();
    }

    public Player get(Player player) throws NotFoundException {
        return get(player.getId());
    }

    public Player move(String id, Direction direction) throws InvalidLinkException, NotFoundException {
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

    public Player play(Player player, String storyworldId) throws NotFoundException {
        Storyworld storyworld = storyworldService.get(storyworldId);
        Progress progress = player.getProgress(storyworldService.get(storyworldId));
        if (progress == null) {
            progress = new StoryworldProgress(storyworld);
            progress.setLocation(storyworld.getEntry());
        }
        // ensure most recent copy of location is set
        progress.setLocation(locationService.get(player.getCurrentProgress().getLocation()));
        player.setCurrentProgress(progress);
        return createOrUpdate(player);
    }

    @Override
    public Player createOrUpdate(Player player) throws NotFoundException {
        super.createOrUpdate(player);
        // ensure current progress has cached location for concurrency
        player.getCurrentProgress().setLocation(locationService.get(player.getCurrentProgress().getLocation()));
        return player;
    }
}
