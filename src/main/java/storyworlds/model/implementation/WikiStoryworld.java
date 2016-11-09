package storyworlds.model.implementation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import storyworlds.model.Location;
import storyworlds.model.Storyworld;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

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
    private String entryText;
    @DBRef(lazy = true)
    private IdentifiedPlayer creator;
    @DBRef(lazy = true)
    private Set<IdentifiedPlayer> maintainers = new ConcurrentSkipListSet<>();
    private boolean isPublic = true;
    private boolean isPubliclyModifiable = true;
    private String color;
    private String backgroundColor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public synchronized Location getEntry() {
        return entry;
    }

    public synchronized void setEntry(Location entry) {
        this.entry = entry;
    }

    public synchronized String getTitle() {
        return title;
    }

    public synchronized void setTitle(String title) {
        this.title = title;
    }

    public synchronized String getDescription() {
        return description;
    }

    public synchronized void setDescription(String description) {
        this.description = description;
    }

    public synchronized String getEntryText() {
        return entryText;
    }

    public synchronized void setEntryText(String entryText) {
        this.entryText = entryText;
    }

    public synchronized IdentifiedPlayer getCreator() {
        return creator;
    }

    public synchronized void setCreator(IdentifiedPlayer creator) {
        this.creator = creator;
    }

    public synchronized Set<IdentifiedPlayer> getMaintainers() {
        return maintainers;
    }

    public synchronized void setMaintainers(Collection<IdentifiedPlayer> maintainers) {
        this.maintainers = new HashSet<>();
        this.maintainers.addAll(maintainers);
    }

    public synchronized boolean isPublic() {
        return isPublic;
    }

    public synchronized void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public synchronized boolean isPubliclyModifiable() {
        return isPubliclyModifiable;
    }

    public void setPubliclyModifiable(boolean publiclyModifiable) {
        isPubliclyModifiable = publiclyModifiable;
    }

    public synchronized String getColor() {
        return color;
    }

    public synchronized void setColor(String color) {
        this.color = color;
    }

    public synchronized String getBackgroundColor() {
        return backgroundColor;
    }

    public synchronized void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public synchronized String toString() {
        return "Storyworld " + title + ", creator: {" + creator + "}, public: " + isPublic;
    }
}
