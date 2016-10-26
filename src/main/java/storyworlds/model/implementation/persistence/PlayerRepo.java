package storyworlds.model.implementation.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import storyworlds.model.implementation.IdentifiedPlayer;

/**
 * Created by nvaughan on 10/19/2016.
 */

public interface PlayerRepo extends MongoRepository<IdentifiedPlayer, String> {
    IdentifiedPlayer findByEmailAndPassword(String email, String password);
}
