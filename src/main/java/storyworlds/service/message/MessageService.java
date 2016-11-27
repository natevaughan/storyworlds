package storyworlds.service.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storyworlds.action.Actionable;

@Service
public class MessageService {

    private              Logger logger             = LoggerFactory.getLogger(getClass());
    private static final String MESSAGE_LOG_MARKER = "msglog,";
    private static final String MESSAGE_STATUS_ERR = "err,";
    private static final String MESSAGE_STATUS_OK  = "ok,";

    @Autowired
    MessageExecutor executor;

    public Actionable process(Message message) throws Exception {

        MessageTransport transport = new MessageTransport(message);

        try {
            transport.accept(new PrimaryMessageParser());
            transport.accept(new SecondaryMessageParser());
            transport.accept(executor);
        } catch (Exception e) {
            logger.info(MESSAGE_LOG_MARKER + MESSAGE_STATUS_ERR + message.getPlayer()
                                                    .getUsername() + "," + message.getCommand() + "," + message.getTime()
                                                                                                               .toEpochMilli());
            // TODO: possibly don't throw error all the way up the chain
            throw e;
        }
        logger.info(MESSAGE_LOG_MARKER + MESSAGE_STATUS_OK + message.getPlayer()
                                                                    .getUsername() + "," + message.getCommand() + "," + message.getTime()
                                                                                                                               .toEpochMilli());
        return transport.getResponse();
    }
}
