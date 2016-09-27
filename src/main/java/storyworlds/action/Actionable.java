package storyworlds.action;

import storyworlds.visitor.ActionVisitor;

public interface Actionable {

    public void accept(ActionVisitor visitor);

    public void setMessage(String message);

    public String getMessage();

    public void setSuccessful(boolean successful);

    public boolean isSuccessful();

}
