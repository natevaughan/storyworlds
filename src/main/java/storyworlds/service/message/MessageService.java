package storyworlds.service.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storyworlds.action.Actionable;

@Service
public class MessageService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MessageExecutor executor;

    public Actionable process(Message message) throws Exception {
        logger.info(message.getPlayer().getUsername() + "," + message.getCommand() + "," + message.getTime().toEpochMilli());

        MessageTransport transport = new MessageTransport(message);

        transport.accept(new PrimaryMessageParser());
        transport.accept(new SecondaryMessageParser());
        transport.accept(executor);

        return transport.getResponse();
    }
}
