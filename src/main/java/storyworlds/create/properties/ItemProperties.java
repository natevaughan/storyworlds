package storyworlds.create.properties;

/**
 * Created by nvaughan on 10/10/2016.
 */
public class ItemProperties extends AbstractCreatableProperties {

    private String name;
    private String useText;

    public String getName() {
        return name;
    }

    public ItemProperties setName(String name) {
        this.name = name;
        return this;
    }

    public String getUseText() {
        return useText;
    }

    public ItemProperties setUseText(String useText) {
        this.useText = useText;
        return this;
    }

    public ItemProperties setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isValid() {
        return name != null && description != null && useText != null;
    }
}
