package storyworlds.model.implementation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import storyworlds.model.implementation.UsableItem;
import storyworlds.model.implementation.User;

import java.io.Serializable;

@Document
public class IdentifiedUser extends User implements Serializable {

    @Id
    private String id;
    private final String email;
    private final String password;

    public IdentifiedUser(String name, String email, String password) {
        super(name);
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public boolean checkPassword(String otherPassword) {
        return this.password.equals(otherPassword);
    }

    @Override
    public String toString() {
        return name + ", " + email + ", " + password;
    }
}
