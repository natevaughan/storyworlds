package storyworlds.model.implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Player;
import storyworlds.model.builder.PlayerBuilder;

/**
 * Created by nvaughan on 10/19/2016.
 */
@Document(collection = "player")
public class IdentifiedPlayer extends AbstractPlayer implements Player, PlayerBuilder {
    @Id
    private String id;
    private String username;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String password;

    public IdentifiedPlayer() {
    };

    public IdentifiedPlayer(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(Collection<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = new LinkedHashSet<>();
        this.grantedAuthorities.addAll(grantedAuthorities);
    }

    @Override
    public String toString() {
        return "IdentifiedPlayer: " + username + ", id: " + id;
    }

    public IdentifiedPlayer build() throws UncreateableException {
        validate(username == null || username.isEmpty(), "Null or empty username");
        validate(email == null || email.isEmpty(), "Null or empty username");
        validate(password == null || password.isEmpty(), "Null or empty username");
        return this;
    }
}
