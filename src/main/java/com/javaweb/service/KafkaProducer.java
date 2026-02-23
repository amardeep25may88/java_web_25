package com.javaweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducer {

    @Autowired
    public KafkaTemplate<String,Object> kafkaTemplate;

    public void sendMsg(String msg) {
        CompletableFuture<SendResult<String, Object>> msgProduced = kafkaTemplate.send("ecommsum", msg);
        msgProduced.whenComplete((stringObjectSendResult, throwable) -> {
            if (throwable == null) {

                System.out.println("Sent Msg=[" + msg + "] with ofset =[" + stringObjectSendResult.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to sen msg =[" + msg + "] due to " + throwable.getMessage());
            }
        });
    }
}


