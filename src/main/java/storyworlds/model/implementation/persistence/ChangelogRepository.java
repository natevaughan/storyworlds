package storyworlds.model.implementation.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import storyworlds.action.Actionable;

/**
 * Created by nvaughan on 11/1/2016.
 */
public interface ChangelogRepository extends MongoRepository<Actionable, String> {
}
