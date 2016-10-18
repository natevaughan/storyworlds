package storyworlds.model.implementation.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import storyworlds.model.Player;

/**
 * Created by nvaughan on 10/15/2016.
 */
public interface PlayerRepository extends MongoRepository<Player, String> {
}
