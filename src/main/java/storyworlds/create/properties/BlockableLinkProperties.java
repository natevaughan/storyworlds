package storyworlds.create.properties;

/**
 * Created by nvaughan on 10/10/2016.
 */
public class BlockableLinkProperties extends DirectionalLinkProperties {

    private String failText;
    private String requiredItem;

    public String getFailText() {
        return failText;
    }

    public BlockableLinkProperties setFailText(String failText) {
        this.failText = failText;
        return this;
    }

    public String getRequiredItem() {
        return requiredItem;
    }

    public BlockableLinkProperties setRequiredItem(String requiredItem) {
        this.requiredItem = requiredItem;
        return this;
    }

    @Override
    public BlockableLinkProperties setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean isValid() {
        return failText != null
                && requiredItem != null
                && super.isValid();
    }
}
