package storyworlds.model.implementation;

public class IdentifiedUser extends User {

    private String email;

    public IdentifiedUser(String name) {
        super(name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
