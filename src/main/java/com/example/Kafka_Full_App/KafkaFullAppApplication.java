package com.example.Kafka_Full_App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class KafkaFullAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaFullAppApplication.class, args);
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Bean
    CommandLineRunner runner() {
        return (args) -> {
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            Runnable runnable = () -> {
                kafkaTemplate.send("topic", "message");
                kafkaTemplate.flush();
                System.out.println("Message sent.");
            };

            for (int i = 0; i < 10; i++) {
                executorService.submit(runnable);
            }
        };
    }
}
