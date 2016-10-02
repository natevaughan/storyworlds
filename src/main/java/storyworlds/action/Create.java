package storyworlds.action;

import storyworlds.model.enumeration.Links;
import storyworlds.model.enumeration.Locations;
import storyworlds.visitor.ActionVisitor;

public class Create extends AbstractCreatableAction {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

    Links linkType;
    Locations locationType;

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
}
