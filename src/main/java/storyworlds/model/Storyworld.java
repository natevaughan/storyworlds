package storyworlds.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Collection;
import storyworlds.model.implementation.IdentifiedPlayer;

/**
 * Created by nvaughan on 10/15/2016.
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
public interface Storyworld extends Persistable {
    Location getEntry();
    void setEntry(Location entry);
    String getTitle();
    String getDescription();
    String getEntryText();
    IdentifiedPlayer getCreator();
    boolean isPublic();
    boolean isPubliclyModifiable();
    Collection<IdentifiedPlayer> getMaintainers();
    String getBackgroundColor();
    String getColor();
}
