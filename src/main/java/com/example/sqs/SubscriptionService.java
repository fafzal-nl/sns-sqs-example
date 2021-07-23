package com.example.sqs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;
import software.amazon.awssdk.services.sns.model.SubscribeResponse;

@Service
@Slf4j
public class SubscriptionService {

    @Value("${access.key}")
    private String accessKey;

    @Value("${secret.key}")
    private String accessSecretKey;

    public void subscribe(String topicArn, String queueUrl) {

        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, accessSecretKey);

        SnsClient snsClient = SnsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();

        SubscribeRequest request = SubscribeRequest.builder()
                .protocol("sqs")
                .topicArn(topicArn)
                .endpoint(queueUrl)
                .returnSubscriptionArn(true)
                .build();

        SubscribeResponse result = snsClient.subscribe(request);

        System.out.println("Subscription ARN: " + result.subscriptionArn() + "\n\n Status was " + result.sdkHttpResponse().statusCode());

    }
}