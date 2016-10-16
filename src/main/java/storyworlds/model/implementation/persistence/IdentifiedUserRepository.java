package storyworlds.model.implementation.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import storyworlds.model.implementation.IdentifiedUser;

/**
 * Created by nvaughan on 10/15/2016.
 */
public interface IdentifiedUserRepository extends MongoRepository<IdentifiedUser, String> {
}
