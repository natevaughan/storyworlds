package storyworlds.model.implementation;

import storyworlds.model.implementation.UsableItem;
import storyworlds.model.implementation.User;

import java.io.Serializable;

public class IdentifiedUser extends User implements Serializable {

    private final String email;
    private final String password;

    public IdentifiedUser(String name, String email, String password) {
        super(name);
        this.email = email;
        this.password = password;
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
