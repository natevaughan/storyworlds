package storyworlds.model.implementation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Item;
import storyworlds.model.ItemBuilder;

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

    public UsableItem(String name, String description, String useMessage) {
        this.name = name;
        this.description = description;
        this.useText = useMessage;
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

    public static class Builder implements ItemBuilder {

        private String name;
        private String description;
        private String useText;

        public static UsableItem.Builder newInstance() {
            return new Builder();
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setUseText(String useText) {
            this.useText = useText;
            return this;
        }

        public Item build() throws UncreateableException {
            validate();
            return new UsableItem(name,description, useText);
        }

        private void validate() throws UncreateableException {
            validate(this.name == null || this.name.isEmpty(), "null or empty description");
            validate(this.description == null || this.description.isEmpty(), "null or empty description");
            validate(this.useText == null || this.useText.isEmpty(), "null or empty use message");
        }

        private void validate(Boolean condition, String message) throws UncreateableException {
            if (condition) {
                throw new UncreateableException(message);
            }
        }
    }
}
