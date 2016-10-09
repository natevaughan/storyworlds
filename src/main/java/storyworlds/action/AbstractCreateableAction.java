package storyworlds.action;

import storyworlds.create.Createable;
import storyworlds.model.enumeration.Links;
import storyworlds.model.enumeration.Locations;

public abstract class AbstractCreateableAction extends DirectionedAction implements CreateableAction {

    protected Createable creatable;
    protected Links linkType;
    protected Locations locationType;
    protected Integer locationIndex;

    public Createable getCreateable() {
        return creatable;
    }

    public void setCreateable(Createable creatable) {
        this.creatable = creatable;
    }

    public void setLinkType(Links linkType) {
        this.linkType = linkType;
    }

    public Links getLinkType() {
        return linkType;
    }

    public Locations getLocationType() {
        return locationType;
    }

    public void setLocationType(Locations locationType) {
        this.locationType = locationType;
    }

    public Integer getLocationIndex() {
        return locationIndex;
    }

    public void setLocationIndex(Integer locationIndex) {
        this.locationIndex = locationIndex;
    }

}
