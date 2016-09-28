package storyworlds.service.message;

import storyworlds.model.Player;

import java.time.Instant;

/**
 * Created by nvaughan on 9/27/2016.
 */
public class Message {

    private Player player;
    private Instant time = Instant.now();
    private String text;

    public Message() {
    }

    public Message(Player player, String text) {
        this.player = player;
        this.text = text;
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

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
