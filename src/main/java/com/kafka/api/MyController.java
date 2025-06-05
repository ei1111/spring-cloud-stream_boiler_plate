package com.kafka.api;

import com.kafka.model.MyMessage;
import com.kafka.producer.MyProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyController {
    private final MyProducer myProducer;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @PostMapping("/message")
    public void message(@RequestBody MyMessage myMessage) {
        //myProducer가 yml의 definition 정의된 myProducer로 가면 producer-test로 가서
        //producer-test.desination인 my-json-topic으로 간다.
        myProducer.sendMessage(myMessage);
    }
}
