package com.kafka.producer;

import com.kafka.model.MyMessage;
import java.util.function.Supplier;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class MyProducer implements Supplier<Flux<Message<MyMessage>>> {
    private final Sinks.Many<Message<MyMessage>> sinks = Sinks.many().unicast().onBackpressureBuffer();

    public MyProducer() {
        System.out.println("MyProducer init!");
    }

    //메시지를 보내는 부분
    //Flux를 사용하지 않으면 기본적인 Supplier 설정은 1초마다 메시지를 생성하게 되어있다.
    //우리는 메시지를 발행하고 싶을 때만 발행하도록 했다.
    //consumer는 실행시키면 뜨고 producer는 발행을 능동적으로 해주어야 하기 때문에 controller로 호출
    public void sendMessage(MyMessage myMessage) {
        Message<MyMessage> message = MessageBuilder
                .withPayload(myMessage)
                .setHeader(KafkaHeaders.KEY, String.valueOf(myMessage.getAge()))
                .build();

        sinks.emitNext(message, Sinks.EmitFailureHandler.FAIL_FAST);
    }

    @Override
    public Flux<Message<MyMessage>> get() {
        return sinks.asFlux();
    }

}
