package sda.java27.rabbitdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class Receiver {

    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public void receiveMessage(String message) {
        log.info("Received <" + message + ">, Received time: " + dateFormat.format(new Date()));
    }

}
