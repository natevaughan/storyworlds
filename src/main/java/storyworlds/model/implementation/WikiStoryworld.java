package storyworlds.model.implementation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import storyworlds.action.Actionable;
import storyworlds.create.Createable;
import storyworlds.model.Location;
import storyworlds.model.Storyworld;

import java.util.Set;

/**
 * Created by nvaughan on 10/15/2016.
 */
@Document(collection = "storyworld")
public class WikiStoryworld implements Storyworld {

    @Id
    private String id;
    @DBRef
    private Location entry;
    private String title;
    private String description;
    private Set<Actionable> changelog;
    private String entryText;
    private IdentifiedPlayer creator;
    private Set<IdentifiedPlayer> maintainers;
    private boolean isPublic;
    private boolean isPubliclyModifiable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public synchronized Location getEntry() {
        return entry.getForwardingLocation();
    }

    public synchronized void setEntry(Location entry) {
        this.entry = entry;
    }

    public String getTitle() {
        return title;
    }

    public synchronized void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public synchronized void setDescription(String description) {
        this.description = description;
    }

    public Set<Actionable> getChangelog() {
        return changelog;
    }

    public void addToChangelog(Actionable actionable) {
        this.changelog.add(actionable);
    };

    public void setChangelog(Set<Actionable> changelog) {
        this.changelog = changelog;
    }

    public String getEntryText() {
        return entryText;
    }

    public void setEntryText(String entryText) {
        this.entryText = entryText;
    }

    public IdentifiedPlayer getCreator() {
        return creator;
    }

    public void setCreator(IdentifiedPlayer creator) {
        this.creator = creator;
    }

    public Set<IdentifiedPlayer> getMaintainers() {
        return maintainers;
    }

    public void setMaintainers(Set<IdentifiedPlayer> maintainers) {
        this.maintainers = maintainers;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public boolean isPubliclyModifiable() {
        return isPubliclyModifiable;
    }

    public void setPubliclyModifiable(boolean publiclyModifiable) {
        isPubliclyModifiable = publiclyModifiable;
    }
}
