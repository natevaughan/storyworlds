package storyworlds.model.implementation.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import storyworlds.model.implementation.persistence.TestEnt;

/**
 * Created by nvaughan on 10/15/2016.
 */
@Component
public interface TestEntRepository extends MongoRepository<TestEnt, String> {
}
