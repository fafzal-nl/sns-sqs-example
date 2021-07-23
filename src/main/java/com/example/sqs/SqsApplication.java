package com.example.sqs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
@Slf4j
public class SqsApplication implements CommandLineRunner {

    final ProducerService producerService;

    @Value("${insert.topicArn}")
    private String topicArn;

    public SqsApplication(ProducerService producerService) {
        this.producerService = producerService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SqsApplication.class, args);
    }

    @Override
    public void run(String... args) {

        log.info("Sample producer message format is {\"offerId\": 10}");

        Scanner scanner = new Scanner(System.in);
        String jsonStr;
        while (!(jsonStr = scanner.nextLine()).equals("")) {
            producerService.publish(topicArn, jsonStr);
        }

        System.exit(1);
    }
}
