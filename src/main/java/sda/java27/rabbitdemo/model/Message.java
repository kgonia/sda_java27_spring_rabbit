package sda.java27.rabbitdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Message {

//    @JsonProperty("message")
    @Size(max = 200)
    @NotBlank
    private String text;

    @Size(max = 30)
    @NotBlank
    private String routingKey;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }
}
