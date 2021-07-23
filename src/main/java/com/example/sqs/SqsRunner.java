package com.example.sqs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;

@Service
@Slf4j
public class SqsRunner {

    @Value("${insert.topicArn}")
    private String topicARN;

    @Value("${access.key}")
    private String accessKey;

    @Value("${secret.key}")
    private String accessSecretKey;

    final SqsService sqsService;

    public SqsRunner(SqsService sqsService) {
        this.sqsService = sqsService;
    }

    @PostConstruct
    public void execute() {

        try {
            sqsService.initialize(topicARN, accessKey, accessSecretKey);
        } catch (JMSException | InterruptedException e) {
            log.error("Error occurs while calling consumer service", e);
        }

    }

}
