package storyworlds.model.builder;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import storyworlds.model.Buildable;
import storyworlds.model.Player;

/**
 * Created by nvaughan on 11/5/2016.
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
public interface PlayerBuilder extends Buildable<Player> {
}
