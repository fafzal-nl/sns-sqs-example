package com.example.sqs;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.policy.Policy;
import com.amazonaws.auth.policy.Principal;
import com.amazonaws.auth.policy.Resource;
import com.amazonaws.auth.policy.Statement;
import com.amazonaws.auth.policy.actions.SQSActions;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SqsService {


    final SubscriptionService subscriptionService;

    public SqsService(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }


    public void initialize(String topicARN, String accessKey, String secretKey) throws JMSException, InterruptedException {

        BasicAWSCredentials creds = new BasicAWSCredentials(accessKey, secretKey);

        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(creds))
                .withRegion(Regions.US_EAST_1)
                .build();

        String myQueue = "MyQueue" + new Date().getTime();
        sqs.createQueue(myQueue);

        GetQueueUrlResult queueUrl = sqs.getQueueUrl(myQueue);

        GetQueueAttributesResult queue_attrs = sqs.getQueueAttributes(
                new GetQueueAttributesRequest(queueUrl.getQueueUrl())
                        .withAttributeNames("QueueArn"));

        String queueArn = queue_attrs.getAttributes().get("QueueArn");

        Policy policy = new Policy().withStatements(
                new Statement(Statement.Effect.Allow)
                        .withPrincipals(Principal.AllUsers)
                        .withResources(new Resource(queueArn))
                        .withActions(SQSActions.AllSQSActions));

        Map<String, String> policyQueueAttributes = new HashMap<>();
        policyQueueAttributes.put(QueueAttributeName.Policy.toString(), policy.toJson());
        sqs.setQueueAttributes(new SetQueueAttributesRequest(queueUrl.getQueueUrl(), policyQueueAttributes));

        subscriptionService.subscribe(topicARN, queueArn);

        ConnectionFactory connectionFactory = new SQSConnectionFactory(
                new ProviderConfiguration(),
                sqs
        );
        Connection connection = connectionFactory.createConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        MessageConsumer consumer = session.createConsumer(session.createQueue(myQueue));

        consumer.setMessageListener(new SqsListener());

        connection.start();

        // Wait for 1 second. The listener onMessage() method is invoked when a message is received.
        Thread.sleep(1000);

    }


}
