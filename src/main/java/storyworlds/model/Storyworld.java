package storyworlds.model;

import storyworlds.model.implementation.IdentifiedUser;

import java.util.Collection;

/**
 * Created by nvaughan on 10/15/2016.
 */
public interface Storyworld {
    public String getId();
    public void setId(String id);
    public Location getEntry();
    public void setEntry(Location entry);
    public String getTitle();
    public void setTitle(String title);
    public String getDescription();
    public void setDescription(String description);
    public String getEntryText();
    public void setEntryText(String entryText);
    public IdentifiedUser getCreator();
    public boolean isPublic();
    public void setPublic(boolean isPublic);
    public boolean isPubliclyModifiable();
    public void setPubliclyModifiable(boolean isPubliclyModifiable);
    public Collection<IdentifiedUser> getMaintainers();
}
