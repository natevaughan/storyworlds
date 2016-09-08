package storyworlds.service.message;

public interface Messenger {

    public void addLine(String text);

    public void send();

    public void send(String text);

    public String getLine();
}
