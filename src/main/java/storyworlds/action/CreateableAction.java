package storyworlds.action;

import storyworlds.create.Createables;
import storyworlds.model.Direction;
import storyworlds.model.enumeration.Links;
import storyworlds.model.enumeration.Locations;


public interface CreateableAction extends Actionable {
    public Createables getCreateable();

    public void setCreateable(Createables creatable);

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
