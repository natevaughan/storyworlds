package storyworlds.action;

import storyworlds.model.Item;

/**
 * Created by nvaughan on 9/9/2016.
 */
public abstract class ItemAction extends AbstractAction {

    private String itemName;

    private boolean successful = false;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
