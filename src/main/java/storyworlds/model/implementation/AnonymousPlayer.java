package storyworlds.model.implementation;

import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by nvaughan on 11/6/2016.
 */
public class AnonymousPlayer extends AbstractPlayer {

    private static final String ANONYMOUS = "ANONYMOUS";

    private final String id;

    public AnonymousPlayer() {
        super();
        this.id =  ANONYMOUS + "-" + UUID.randomUUID();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ANONYMOUS_USER");
        this.grantedAuthorities.add(grantedAuthority);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        throw new NotImplementedException();
    }

    public String getUsername() {
        return ANONYMOUS;
    }

    public String getPassword() { return ANONYMOUS; }

}
