package storyworlds.model.implementation.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import storyworlds.model.implementation.TestBidirectionalChildEntity;

/**
 * Created by nvaughan on 10/16/2016.
 */
public interface TestBidirectionalChildEntityRepository extends MongoRepository<TestBidirectionalChildEntity, String> {
}
