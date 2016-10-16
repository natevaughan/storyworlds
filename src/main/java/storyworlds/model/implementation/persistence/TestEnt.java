package storyworlds.model.implementation.persistence;

import org.springframework.data.annotation.Id;

/**
 * Created by nvaughan on 10/15/2016.
 */
public class TestEnt {

    @Id
    private String id;

    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "{" + id + " " + text + "}";
    }
}
