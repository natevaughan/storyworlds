package storyworlds;

import storyworlds.constants.GameTextConstants;
import storyworlds.factory.MapFactory;
import storyworlds.model.Location;
import storyworlds.model.Player;
import storyworlds.model.implementation.User;
import storyworlds.service.console.ConsoleInterface;
import storyworlds.service.message.ConsoleMessenger;
import storyworlds.service.message.Message;
import storyworlds.service.message.MessageService;
import storyworlds.service.message.Messenger;

public class Application implements GameTextConstants {

    public static void main(String[] args) {
        ConsoleInterface console = new ConsoleInterface();
        console.run();
    }
}
