package storyworlds.service.message;

import java.util.Scanner;

public class ConsoleMessenger implements Messenger {

    private StringBuilder sb = new StringBuilder();
    private Scanner scanner = new Scanner(System.in);

    public void addLine(String text) {
        sb.append("\n");
        sb.append(text); 
    }

    public void send() {
        System.out.println(sb.toString());
        sb.delete(0, sb.length());
    }

    public void send(String text) {
        addLine(text);
        send();
    }
    
    public String getLine() {
        return scanner.nextLine();
    }
}
