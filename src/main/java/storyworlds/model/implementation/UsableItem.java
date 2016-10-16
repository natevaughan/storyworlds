package storyworlds.model.implementation;

import org.springframework.data.annotation.PersistenceConstructor;
import storyworlds.model.Item;

/**
 * Created by nvaughan on 9/10/2016.
 */
public class UsableItem implements Item {

    private final String name;
    private final String description;
    private final String useMessage;

    @PersistenceConstructor
    public UsableItem(String name, String description, String useMessage) {
        this.name = name;
        this.description = description;
        this.useMessage = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsableItem that = (UsableItem) o;

        if (!name.equals(that.name)) return false;
        if (!description.equals(that.description)) return false;
        return useMessage.equals(that.useMessage);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + useMessage.hashCode();
        return result;
    }
}
