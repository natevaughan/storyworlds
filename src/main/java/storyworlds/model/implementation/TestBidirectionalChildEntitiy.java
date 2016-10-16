package storyworlds.model.implementation;

import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Created by nvaughan on 10/16/2016.
 */
public class TestBidirectionalChildEntitiy {

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
}
