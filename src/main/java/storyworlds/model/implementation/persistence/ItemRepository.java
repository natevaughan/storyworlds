package storyworlds.model.implementation.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import storyworlds.model.Item;

/**
 * Created by nvaughan on 10/19/2016.
 */
public interface ItemRepository extends MongoRepository<Item, String>{
}
