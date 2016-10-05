package storyworlds.action;

import storyworlds.create.Createables;
import storyworlds.model.Direction;
import storyworlds.model.enumeration.Links;
import storyworlds.model.enumeration.Locations;

public abstract class AbstractCreateableAction extends DirectionedAction implements CreateableAction {

    private Createables creatable;
    private Links linkType;
    private Locations locationType;
    private Integer locationIndex;

    public Createables getCreateable() {
        return creatable;
    }

    public void setCreateable(Createables creatable) {
        this.creatable = creatable;
    }

    public boolean isCreateable() {
        return creatable != null && direction != null && !Createables.ERROR.equals(creatable) && !Direction.ERROR.equals(direction);
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