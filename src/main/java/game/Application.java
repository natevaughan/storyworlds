package game;

import game.constants.GameTextConstants;
import game.model.*;
import game.model.implementation.*;
import game.factory.MapFactory;
import game.gameplay.*;

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
