package storyworlds.create.properties;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import storyworlds.AbstractSpringTest;
import storyworlds.model.implementation.TestBidirectionalChildEntity;
import storyworlds.model.implementation.TestBidirectionalEntity;
import storyworlds.model.implementation.persistence.TestBidirectionalChildEntityRepository;
import storyworlds.model.implementation.persistence.TestBidirectionalEntityRepository;

import static org.junit.Assert.assertNotNull;

/**
 * Created by nvaughan on 10/16/2016.
 */
public class TestBidirectionalParentChildTest extends AbstractSpringTest {

    @Autowired
    TestBidirectionalEntityRepository repo;

    @Autowired
    TestBidirectionalChildEntityRepository childRepo;

    @Test
    public void te() {
        TestBidirectionalEntity entity = new TestBidirectionalEntity();
        entity.setName("parent");
        TestBidirectionalChildEntity child = new TestBidirectionalChildEntity();
        child.setName("child");
        assertNotNull(repo);
        assertNotNull(childRepo);


    }
}
