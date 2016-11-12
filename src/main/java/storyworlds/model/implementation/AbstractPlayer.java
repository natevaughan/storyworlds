package storyworlds.model.implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import storyworlds.model.Player;
import storyworlds.model.Progress;
import storyworlds.model.Storyworld;
import storyworlds.model.builder.AbstractBuilder;

/**
 * Created by nvaughan on 11/12/2016.
 */
public abstract class AbstractPlayer extends AbstractBuilder implements Player {

    @JsonIgnore
    protected Set<GrantedAuthority> grantedAuthorities = new LinkedHashSet<>();
    protected Set<Progress> progress = new LinkedHashSet<>();
    protected Progress currentProgress;

    protected AbstractPlayer() {
    }

    public Set<Progress> getProgress() {
        return progress;
    }

    public Progress getProgress(Storyworld storyworld) {
        for (Progress p : this.progress) {
            if (p.getStoryworld().equals(storyworld)) {
                return p;
            }
        }
        return null;
    }

    public void setProgress(Set<Progress> progress) {
        this.progress = progress;
    }

    public Progress getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(Progress currentProgress) {
        this.progress.add(currentProgress);
        this.currentProgress = currentProgress;
    }

    public Collection<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(Collection<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities.clear();
        this.grantedAuthorities.addAll(grantedAuthorities);
    }
}
