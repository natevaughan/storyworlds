package storyworlds.model;

import java.util.Collection;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Document(collection = "player")
public interface Player extends Persistable {

    String getUsername();

    Collection<GrantedAuthority> getGrantedAuthorities();

    void setGrantedAuthorities(Collection<GrantedAuthority> grantedAuthorities);

    Collection<Progress> getProgress();

    Progress getProgress(Storyworld storyworld);

    Progress getCurrentProgress();

    void setCurrentProgress(Progress progress);
}
