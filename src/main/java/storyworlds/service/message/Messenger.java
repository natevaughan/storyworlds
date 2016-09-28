package storyworlds.service.message;

public interface Messenger {

    public void addLine(String text);

    public void sendMessage();

    public void sendMessage(String message);

    public Message getMessage();
}
