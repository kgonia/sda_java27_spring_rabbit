package sda.java27.rabbitdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.java27.rabbitdemo.config.RabbitConfiguration;
import sda.java27.rabbitdemo.model.Message;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/message")
public class MessageController {

    private final RabbitTemplate rabbitTemplate;

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public void sendMessage(@RequestBody @Valid Message message) {
        log.info("Message received: " + message.getText());
        rabbitTemplate.convertAndSend(RabbitConfiguration.topicExchangeName,
                message.getRoutingKey(),
                message.getText());
    }
}
