package storyworlds.model.builder;

import storyworlds.exception.UncreateableException;
import storyworlds.model.Item;
import storyworlds.model.Player;
import storyworlds.model.implementation.UsableItem;

/**
 * Created by nvaughan on 11/5/2016.
 */
public class UsableItemBuilder extends AbstractBuilder implements ItemBuilder {

    private String name;
    private String description;
    private String useText;
    private Player creator;

    public UsableItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UsableItemBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public UsableItemBuilder setUseText(String useText) {
        this.useText = useText;
        return this;
    }

    public UsableItemBuilder setCreator(Player creator) {
        this.creator = creator;
        return this;
    }

    public Item build() throws UncreateableException {
        validate();
        return new UsableItem(name,description, useText, creator);
    }

    private void validate() throws UncreateableException {
        validate(this.name == null || this.name.isEmpty(), "null or empty description");
        validate(this.description == null || this.description.isEmpty(), "null or empty description");
        validate(this.useText == null || this.useText.isEmpty(), "null or empty use message");
        validate(this.creator == null, "null creator");
    }
}
