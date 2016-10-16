package storyworlds.model.implementation.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import storyworlds.model.Storyworld;

/**
 * Created by nvaughan on 10/16/2016.
 */
public interface StoryworldRepository extends MongoRepository<Storyworld, String> {
}
