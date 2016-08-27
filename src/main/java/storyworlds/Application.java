package storyworlds;

import storyworlds.constants.GameTextConstants;
import storyworlds.factory.MapFactory;
import storyworlds.gameplay.*;
import storyworlds.model.*;
import storyworlds.model.implementation.*;

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
