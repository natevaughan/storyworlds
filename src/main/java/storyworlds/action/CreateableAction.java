package storyworlds.action;

import storyworlds.create.Createable;
import storyworlds.model.Direction;
import storyworlds.model.enumeration.Links;
import storyworlds.model.enumeration.Locations;


public interface CreateableAction extends Actionable {
    public Createable getCreateable();

    public void setCreateable(Createable creatable);

    public void setDirection(Direction direction);

    public Direction getDirection();

    public boolean isCreateable();

    public void setLinkType(Links linkType);

    public Links getLinkType();

    public Locations getLocationType();

    public void setLocationType(Locations locationType);

    public Integer getLocationIndex();

    public void setLocationIndex(Integer locationIndex);
}
