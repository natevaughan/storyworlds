package storyworlds.service.console;

import storyworlds.action.Actionable;
import storyworlds.action.Quit;
import storyworlds.constants.GameTextConstants;
import storyworlds.factory.MapFactory;
import storyworlds.model.Location;
import storyworlds.model.Player;
import storyworlds.model.implementation.User;
import storyworlds.service.message.Message;
import storyworlds.service.message.MessageService;

import java.util.Scanner;

public class ConsoleIO implements GameTextConstants {

    private StringBuilder sb = new StringBuilder();
    MessageService messageService = new MessageService();
    private Scanner scanner = new Scanner(System.in);
    private Player player;

    public void run() {
        sendMessage(WELCOME_MESSAGE);
        String name = getMessage();
        Location start = MapFactory.getStartMap();
        player = new User(name);
        player.setLocation(start);
        Actionable response = messageService.process(new Message(player, "status"));
        while (!Quit.class.equals(response.getClass())) {
            sendMessage("What's your next move?");
            response = messageService.process(new Message(player, getMessage()));
            sendMessage(response.getMessage().getText());
        }
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

    public String getMessage() {
        return scanner.nextLine();
    }
}
