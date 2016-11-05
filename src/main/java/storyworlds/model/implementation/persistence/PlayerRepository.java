package storyworlds.model.implementation.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import storyworlds.model.Player;
import storyworlds.model.implementation.IdentifiedPlayer;

/**
 * Created by nvaughan on 10/19/2016.
 */

public interface PlayerRepository extends MongoRepository<Player, String> {
    IdentifiedPlayer findByEmailAndPassword(String email, String password);
    IdentifiedPlayer findByUsername(String username);
    IdentifiedPlayer findByEmail(String email);
}
