package sda.java27.rabbitdemo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import sda.java27.rabbitdemo.config.RabbitConfiguration;
import sda.java27.rabbitdemo.data.MessageDB;
import sda.java27.rabbitdemo.repository.MessageRepository;

@Component
public class AllMessagesListener {

    private final MessageRepository messageRepository;

    private static final Logger log = LoggerFactory.getLogger(AllMessagesListener.class);

    public AllMessagesListener(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @RabbitListener(queues = RabbitConfiguration.queueAllMessages)
    public void handleMessage(String text, Message message){

        String routingKey = (String) message.getHeaders().get("amqp_receivedRoutingKey");
        log.info("Message: " + text + " routing key: " + routingKey);

        MessageDB messageDB = new MessageDB();
        messageDB.setText(text);
        messageDB.setRoutingKey(routingKey);
        messageRepository.save(messageDB);
        log.info("Message saved");
    }

}
