package storyworlds.service.message;

import storyworlds.model.Player;

import java.time.Instant;

/**
 * Created by nvaughan on 9/27/2016.
 */
public class Message {

    private Player player;
    private Instant time = Instant.now();
    private String command;
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
    
    public void addLine(String text) {
        sb.append("\n");
        sb.append(text);
    }

    public void resetText() {
        sb.setLength(0);
    }
}
