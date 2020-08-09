package sda.java27.rabbitdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import sda.java27.rabbitdemo.config.RabbitConfiguration;
import sda.java27.rabbitdemo.data.MessageDB;
import sda.java27.rabbitdemo.model.Message;
import sda.java27.rabbitdemo.repository.MessageRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/message")
public class MessageController {

    private final RabbitTemplate rabbitTemplate;

    private final MessageRepository messageRepository;

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    public MessageController(RabbitTemplate rabbitTemplate, MessageRepository messageRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageRepository = messageRepository;
    }

    @PostMapping
    public void sendMessage(@RequestBody @Valid Message message) {
        log.info("Message received: " + message.getText());
        rabbitTemplate.convertAndSend(RabbitConfiguration.topicExchangeName,
                message.getRoutingKey(),
                message.getText());
    }

    @GetMapping
    public List<MessageDB> findAllMessages(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "size", defaultValue = "100") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return messageRepository.findAll(pageRequest).getContent();
    }
}
