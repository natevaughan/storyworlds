package storyworlds.model.implementation.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import storyworlds.model.implementation.Player2;

/**
 * Created by nvaughan on 10/19/2016.
 */
public interface Player2Repo extends MongoRepository<Player2, String> {
}
