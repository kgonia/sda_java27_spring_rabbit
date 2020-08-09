package sda.java27.rabbitdemo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Random;
import java.util.stream.Stream;

@RestController
public class MessageReactiveController {

    private final Random random = new Random();

    @GetMapping(value = "/streaming", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<BigDecimal> getItemsStream() {
        return Flux
                .fromStream(Stream.generate(() -> BigDecimal.valueOf(Math.abs(random.nextLong()))))
                .delayElements(Duration.ofSeconds(1));
//        return Flux.interval(Duration.ofSeconds(1)); // You can write your own logic here.
    }
}

