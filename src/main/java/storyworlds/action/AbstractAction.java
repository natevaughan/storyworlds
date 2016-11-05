package storyworlds.action;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import storyworlds.model.Storyworld;
import storyworlds.service.message.Message;

@Document
public abstract class AbstractAction implements Actionable {

    protected String id;

    @DBRef
    protected Storyworld storyworld;

    protected Message message = new Message();
    protected boolean successful = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Storyworld getStoryworld() {
        return storyworld;
    }

    public void setStoryworld(Storyworld storyworld) {
        this.storyworld = storyworld;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

}
