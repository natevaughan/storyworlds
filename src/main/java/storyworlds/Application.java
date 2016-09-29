package storyworlds;

import storyworlds.constants.GameTextConstants;
import storyworlds.service.console.ConsoleIO;

public class Application implements GameTextConstants {

    public static void main(String[] args) {
        ConsoleIO console = new ConsoleIO();
        console.run();
    }
}
