package storyworlds.model.implementation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import storyworlds.model.Location;
import storyworlds.model.Storyworld;

import java.io.Serializable;

/**
 * Created by nvaughan on 10/15/2016.
 */
@Document
public class WikiStoryworld implements Storyworld, Serializable {

    @Id
    private String id;
    @DBRef
    private Location entry;
//    private String title;
//    private String description;
//    private String entryText;
//    private IdentifiedUser creator;
//    private Set<IdentifiedUser> maintainers;
//    private boolean isPublic;
//    private boolean isPubliclyModifiable;
//
//    public WikiStoryworld() {
//        this.creator = creator;
//        this.maintainers = new HashSet<>();
//    }
//
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getEntry() {
        return entry;
    }

    public void setEntry(Location entry) {
        this.entry = entry;
    }

//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getEntryText() {
//        return entryText;
//    }
//
//    public void setEntryText(String entryText) {
//        this.entryText = entryText;
//    }
//
//    public IdentifiedUser getCreator() {
//        return creator;
//    }
//
//    public Collection<IdentifiedUser> getMaintainers() {
//        return maintainers;
//    }
//
//    public boolean isPublic() {
//        return isPublic;
//    }
//
//    public void setPublic(boolean aPublic) {
//        isPublic = aPublic;
//    }
//
//    public boolean isPubliclyModifiable() {
//        return isPubliclyModifiable;
//    }
//
//    public void setPubliclyModifiable(boolean publiclyModifiable) {
//        isPubliclyModifiable = publiclyModifiable;
//    }

    @Override
    public String toString() {
        return "id: " + id + "\nentry: " + entry.getDescription();
    }
}