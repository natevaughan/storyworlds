package storyworlds.model.implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import storyworlds.model.Item;
import storyworlds.model.Player;

/**
 * Created by nvaughan on 9/10/2016.
 */
@Document(collection = "item")
public class UsableItem implements Item {

    @Id
    String id;
    private final String name;
    private final String description;
    private final String useText;
    @JsonIgnore
    @DBRef(lazy = true)
    private final Player creator;

    public UsableItem(String name, String description, String useText, Player creator) {
        this.name = name;
        this.description = description;
        this.useText = useText;
        this.creator = creator;
    }

    public synchronized String getId() {
        return id;
    }

    public synchronized void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUseText() {
        return useText;
    }

    public Player getCreator() {
        return creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsableItem that = (UsableItem) o;

        if (!name.equals(that.name)) return false;
        if (!description.equals(that.description)) return false;
        return useText.equals(that.useText);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + useText.hashCode();
        return result;
    }

    @Override
    public int compareTo(Item o) {
        return name.compareTo(o.getName());
    }

}
