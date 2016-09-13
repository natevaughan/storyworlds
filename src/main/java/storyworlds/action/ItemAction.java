package storyworlds.action;

import storyworlds.model.Item;

/**
 * Created by nvaughan on 9/9/2016.
 */
public abstract class ItemAction extends AbstractAction {

    private String itemName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
