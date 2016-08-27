package storyworlds;

import storyworlds.constants.GameTextConstants;
import storyworlds.factory.MapFactory;
import storyworlds.gameplay.Executor;
import storyworlds.model.Action;
import storyworlds.model.Location;
import storyworlds.model.Messenger;
import storyworlds.model.implementation.ConsoleMessenger;
import storyworlds.model.implementation.User;

public class Application implements GameTextConstants {

    public static void main(String[] args) {
        Messenger m = new ConsoleMessenger();
        m.send(WELCOME_MESSAGE);
        String name = m.getLine(); 
        Location start = MapFactory.getStartMap();
        User user = new User(name);
        user.setLocation(start);
        Executor e = new Executor(user); 
        m.addLine(e.getPlayer().getLocation().getText());
        Action action = Action.ERROR;
        while (!Action.QUIT.equals(action)) { 
            m.send("What's your next move?");
            action = e.execute(m.getLine());
        }
    }
}
