package storyworlds.model.implementation;

import storyworlds.model.Item;

/**
 * Created by nvaughan on 9/10/2016.
 */
public class UsableItem implements Item {

    private final String name;
    private final String description;
    private final String useMessage;
    private boolean active;

    public UsableItem(String name, String description, String useMessage) {
        this.name = name;
        this.description = description;
        this.useMessage = description;
        this.active = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUseMessage() {
        return useMessage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsableItem that = (UsableItem) o;

        if (active != that.active) return false;
        if (!name.equals(that.name)) return false;
        return description.equals(that.description);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (active ? 1 : 0);
        return result;
    }
}
