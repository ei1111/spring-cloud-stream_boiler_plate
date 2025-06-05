package com.kafka.consumer;


import com.kafka.model.MyMessage;
import java.util.function.Consumer;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MyConsumer implements Consumer<Message<MyMessage>> {

    public MyConsumer() {
        System.out.println("MyConsumer init!");
    }

    @Override
    public void accept(Message<MyMessage> message) {
        System.out.println("message = " + message.getPayload());
    }
}
