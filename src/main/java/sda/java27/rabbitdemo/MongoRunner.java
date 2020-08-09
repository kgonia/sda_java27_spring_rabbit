package sda.java27.rabbitdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sda.java27.rabbitdemo.data.MessageDB;
import sda.java27.rabbitdemo.repository.MessageRepository;

import java.util.List;

//@Component
public class MongoRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MongoRunner.class);

    private final MessageRepository messageRepository;

    public MongoRunner(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        messageRepository.deleteAll();

        MessageDB messageDB1 = new MessageDB();
        messageDB1.setText("To jest teksts");
        messageDB1.setRoutingKey("key");

        MessageDB messageDB2 = new MessageDB();
        messageDB2.setText("To jest tekst");
        messageDB2.setRoutingKey("key2");

        messageRepository.saveAll(List.of(messageDB1, messageDB2));

        log.info("Messages saved");

        messageRepository.findAll()
                .forEach(MongoRunner::logMessage);

    }

    private static void logMessage(MessageDB messageDB) {
        log.info("Message from DB:");
        log.info(messageDB.getId());
        log.info(messageDB.getText());
    }
}



