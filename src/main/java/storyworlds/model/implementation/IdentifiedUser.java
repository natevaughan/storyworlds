package storyworlds.model.implementation;

import java.io.Serializable;

public class IdentifiedUser extends User implements Serializable {

    private String email;
    private String password;

    public IdentifiedUser(String name) {
        super(name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean checkPassword(String otherPassword) {
        return this.password.equals(otherPassword);
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
