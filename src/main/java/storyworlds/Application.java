package storyworlds;

import storyworlds.constants.GameTextConstants;
import storyworlds.factory.MapFactory;
import storyworlds.model.Location;
import storyworlds.model.Player;
import storyworlds.model.implementation.User;
import storyworlds.service.message.ConsoleMessenger;
import storyworlds.service.message.Message;
import storyworlds.service.message.MessageService;
import storyworlds.service.message.Messenger;

public class Application implements GameTextConstants {

    public static void main(String[] args) {
        Player system = new User("system");
        Messenger m = new ConsoleMessenger(system);
        m.sendMessage(WELCOME_MESSAGE);
        Message name = m.getMessage();
        Location start = MapFactory.getStartMap();
        User user = new User(name.getText());
        user.setLocation(start);
        m = new ConsoleMessenger(user);
        MessageService messageService = new MessageService();
//        Executor e = new Executor(user);
        messageService.process(new Message(user, "status"));
        while (true) {
            m.sendMessage("What's your next move?");
            messageService.process(m.getMessage());
        }
    }
}
