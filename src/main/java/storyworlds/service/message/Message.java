package storyworlds.service.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.Instant;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import storyworlds.model.Player;

/**
 * Created by nvaughan on 9/27/2016.
 */
@Document
public class Message {

    @DBRef
    @JsonIgnore
    private Player player;
    private Instant time = Instant.now();
    private String command;
    @Transient
    private StringBuilder sb = new StringBuilder();

    public Message() {
    }

    public Message(Player player, String command) {
        this.player = player;
        this.command = command;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Instant getTime() {
        return time;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getText() {
        return sb.toString();
    }

    public void setText(String text) {
        // no op for Jackson
    };

    public void addLine(String text) {
        sb.append("\n");
        sb.append(text);
    }

    public void resetText() {
        sb.setLength(0);
    }
}
