package storyworlds.model.implementation.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import storyworlds.model.Location;

/**
 * Created by nvaughan on 10/16/2016.
 */
public interface LocationRepository extends MongoRepository<Location, String> {
}
