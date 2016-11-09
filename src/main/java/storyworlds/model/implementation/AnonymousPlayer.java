package storyworlds.model.implementation;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import storyworlds.model.Player;
import storyworlds.model.Progress;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by nvaughan on 11/6/2016.
 */
public class AnonymousPlayer implements Player {

    private static final String ANONYMOUS = "ANONYMOUS";

    private final String id;
    private final Set<GrantedAuthority> grantedAuthorities;
    private final Set<Progress> progress;
    private Progress currentProgress;

    public AnonymousPlayer() {
        this.id =  ANONYMOUS + "-" + UUID.randomUUID();
        this.grantedAuthorities = new HashSet<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ANONYMOUS_USER");
        this.grantedAuthorities.add(grantedAuthority);
        this.progress = new HashSet<>();
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

    public Collection<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(Collection<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities.clear();
        this.grantedAuthorities.addAll(grantedAuthorities);
    }

    public Collection<Progress> getProgress() {
        return progress;
    }

    public Progress getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(Progress progress) {
        if (this.progress.contains(progress)) {
            this.progress.add(progress);
        }
        this.currentProgress = progress;
    }
}
