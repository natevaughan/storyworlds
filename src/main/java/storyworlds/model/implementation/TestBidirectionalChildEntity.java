package storyworlds.model.implementation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Created by nvaughan on 10/16/2016.
 */
public class TestBidirectionalChildEntity {

    @Id
    private String id;
    private String name;
    @DBRef(lazy = true)
    private TestBidirectionalEntity bidirectionalEntitiy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestBidirectionalEntity getBidirectionalEntitiy() {
        return bidirectionalEntitiy;
    }

    public void setBidirectionalEntitiy(TestBidirectionalEntity bidirectionalEntitiy) {
        this.bidirectionalEntitiy = bidirectionalEntitiy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
