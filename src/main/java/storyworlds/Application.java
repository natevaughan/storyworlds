package storyworlds;

import storyworlds.action.Action;
import storyworlds.constants.GameTextConstants;
import storyworlds.factory.MapFactory;
import storyworlds.gameplay.Executor;
import storyworlds.model.Location;
import storyworlds.model.implementation.User;
import storyworlds.service.message.ConsoleMessenger;
import storyworlds.service.message.Messenger;

public class Application implements GameTextConstants {

    public static void main(String[] args) {
        Messenger m = new ConsoleMessenger();
        m.send(WELCOME_MESSAGE);
        String name = m.getLine(); 
        Location start = MapFactory.getStartMap();
        User user = new User(name);
        user.setLocation(start);
        Executor e = new Executor(user);
        Action action = Action.ERROR;
        action = e.execute("status");
        while (!Action.QUIT.equals(action)) {
            m.send("What's your next move?");
            action = e.execute(m.getLine());
        }
    }
}
