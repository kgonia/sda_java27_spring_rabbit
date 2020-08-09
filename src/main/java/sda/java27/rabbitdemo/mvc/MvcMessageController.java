package sda.java27.rabbitdemo.mvc;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sda.java27.rabbitdemo.config.RabbitConfiguration;
import sda.java27.rabbitdemo.model.Message;

@Controller
@RequestMapping("/mvc/message")
public class MvcMessageController {

    private final RabbitTemplate rabbitTemplate;

    public MvcMessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public String greetingForm(Model model) {
        model.addAttribute("message", new Message());
        return "message";
    }

    @PostMapping
    public String greetingSubmit(@ModelAttribute Message message, Model model) {
        model.addAttribute("message", message);
        rabbitTemplate.convertAndSend(RabbitConfiguration.topicExchangeName,
                message.getRoutingKey(),
                message.getText());
        return "result";
    }


}
