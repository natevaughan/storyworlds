package storyworlds.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import storyworlds.model.implementation.StoryworldProgress;

import java.util.Collection;

@Document(collection = "player")
public interface Player extends Persistable {

    String getUsername();

    Collection<GrantedAuthority> getGrantedAuthorities();

    void setGrantedAuthorities(Collection<GrantedAuthority> grantedAuthorities);

    Collection<Progress> getProgress();

    Progress getCurrentProgress();

    void setCurrentProgress(Progress progress);
}
