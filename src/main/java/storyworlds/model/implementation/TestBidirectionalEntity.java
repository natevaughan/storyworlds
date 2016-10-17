package storyworlds.model.implementation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.UUID;

/**
 * Created by nvaughan on 10/16/2016.
 */
public class TestBidirectionalEntity {

    @Id
    private String id;
    private String name;
    @DBRef
    private TestBidirectionalChildEntity childEntitiy;

    public TestBidirectionalEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestBidirectionalChildEntity getChildEntitiy() {
        return childEntitiy;
    }

    public void setChildEntitiy(TestBidirectionalChildEntity childEntitiy) {
        this.childEntitiy = childEntitiy;
    }

    @Override
    public String toString() {
        return getClass().toString() + " with id : " + id + ", name: " + name + ", child " + childEntitiy.getName();
    }
}
