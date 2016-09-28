package storyworlds.service.message;

import storyworlds.model.Player;

import java.util.Scanner;

public class ConsoleMessenger implements Messenger {

    private StringBuilder sb = new StringBuilder();
    private Scanner scanner = new Scanner(System.in);
    private final Player player;

    public ConsoleMessenger(Player player) {
        this.player = player;
    }

    public void addLine(String text) {
        sb.append("\n");
        sb.append(text); 
    }

    public void sendMessage() {
        System.out.println(sb.toString());
        sb.delete(0, sb.length());
    }

    public void sendMessage(String message) {
        addLine(message);
        sendMessage();
    }
    
    public Message getMessage() {
        return new Message(player, scanner.nextLine());
    }
}
