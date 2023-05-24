package com.example.Kafka_Full_App;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaListener {

    @KafkaListener(id = "myListener", topics = "topic", groupId = "g2",
            autoStartup = "${spring.kafka.listener.auto-startup}", concurrency = "${listen.concurrency:3}")
    public void listenGroupFoo(ConsumerRecord<Integer, String> record) {
        System.out.println("Received message in group: " + record.value() );

    }
}