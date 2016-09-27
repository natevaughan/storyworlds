package storyworlds;

import storyworlds.action.ActionFactory;
import storyworlds.action.Actionable;
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
        Actionable actionable = ActionFactory.get("error");
        actionable = e.execute("status");
        while (!Error.class.equals(actionable.getClass())) {
            m.send("What's your next move?");
            actionable = e.execute(m.getLine());
        }
    }
}
