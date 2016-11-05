package storyworlds.model;

import storyworlds.model.implementation.IdentifiedPlayer;

import java.util.Collection;

/**
 * Created by nvaughan on 10/15/2016.
 */
public interface Storyworld extends Persistable {
    Location getEntry();
    void setEntry(Location entry);
    String getTitle();
    void setTitle(String title);
    String getDescription();
    void setDescription(String description);
    String getEntryText();
    void setEntryText(String entryText);
    IdentifiedPlayer getCreator();
    boolean isPublic();
    void setPublic(boolean isPublic);
    boolean isPubliclyModifiable();
    void setPubliclyModifiable(boolean isPubliclyModifiable);
    Collection<IdentifiedPlayer> getMaintainers();
    void setMaintainers(Collection<IdentifiedPlayer> maintainers);
    String getBackgroundColor();
    void setBackgroundColor(String backgroundColor);
}
