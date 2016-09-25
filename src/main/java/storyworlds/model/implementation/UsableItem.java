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
        if (name != null ? !name.equalsIgnoreCase(that.name) : that.name != null) return false;
        if (description != null ? !description.equalsIgnoreCase(that.description) : that.description != null) return false;
        return useMessage != null ? useMessage.equalsIgnoreCase(that.useMessage) : that.useMessage == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (useMessage != null ? useMessage.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }
}
