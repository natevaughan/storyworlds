package storyworlds.model.builder;

import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import storyworlds.action.parser.HexColor;
import storyworlds.exception.InvalidColorException;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Storyworld;
import storyworlds.model.implementation.IdentifiedPlayer;
import storyworlds.model.implementation.WikiStoryworld;

/**
 * Created by nvaughan on 11/12/2016.
 */
public class WikiStoryworldBuilder extends AbstractBuilder implements StoryworldBuilder {

    private LocationBuilder              entryBuilder;          // optional for location to be built then bound
    private String                       title;
    private String                       description;
    private String                       entryText;
    private IdentifiedPlayer             creator;
    private Collection<IdentifiedPlayer> maintainers;           // optional
    private Boolean                      visible;
    private Boolean                      publiclyModifiable;
    private String                       color;                 // optional
    private String                       backgroundColor;       //optional

    public WikiStoryworldBuilder setEntryBuilder(LocationBuilder entryBuilder) {
        this.entryBuilder = entryBuilder;
        return this;
    }

    public LocationBuilder getEntryBuilder() {
        return entryBuilder;
    }

    public WikiStoryworldBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public WikiStoryworldBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public WikiStoryworldBuilder setEntryText(String entryText) {
        this.entryText = entryText;
        return this;
    }

    public WikiStoryworldBuilder setCreator(IdentifiedPlayer creator) {
        this.creator = creator;
        return this;
    }

    public WikiStoryworldBuilder setMaintainers(
            Collection<IdentifiedPlayer> maintainers) {
        this.maintainers = maintainers;
        return this;
    }

    public WikiStoryworldBuilder setVisible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public WikiStoryworldBuilder setPubliclyModifiable(Boolean publiclyModifiable) {
        this.publiclyModifiable = publiclyModifiable;
        return this;
    }

    public WikiStoryworldBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public WikiStoryworldBuilder setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    /**
     * Builds a new storyworld but does not set its entry location
     *
     * @throws UncreateableException
     */
    public Storyworld build() throws UncreateableException {
        validate(StringUtils.isEmpty(this.title), "empty title");
        validate(StringUtils.isEmpty(this.description), "empty description");
        validate(StringUtils.isEmpty(this.entryText), "empty entry text");
        validate(this.creator == null, "null creator");
        validate(this.visible == null, "null public field");
        validate(this.publiclyModifiable == null, "null publicly modifiable field");

        try {
            if (this.color != null) {
                this.color = HexColor.validate(this.color);
            } else {
                this.color = "#f0fafd";
            }
            if (this.backgroundColor != null) {
                this.backgroundColor = HexColor.validate(this.backgroundColor);
            } else {
                this.backgroundColor = "#111a1f";
            }
        } catch (InvalidColorException e) {
            throw new UncreateableException(e);
        }

        WikiStoryworld storyworld = new WikiStoryworld(this.creator);

        // non atomic operations be thee warned
        storyworld.setTitle(this.title);
        storyworld.setDescription(this.description);
        storyworld.setEntryText(this.entryText);
        storyworld.setPublic(this.visible);
        storyworld.setPubliclyModifiable(this.publiclyModifiable);
        storyworld.setBackgroundColor(this.backgroundColor);
        storyworld.setColor(this.color);
        if (this.maintainers != null) {
            storyworld.setMaintainers(this.maintainers);
        }

        return storyworld;
    }
}
