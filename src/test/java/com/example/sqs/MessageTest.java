package com.example.sqs;

import com.example.sqs.model.SqsMessage;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

public class MessageTest {


    @Test
    void testGson() {

        String str = "{\n" +
                "  \"Type\" : \"Notification\",\n" +
                "  \"MessageId\" : \"f06fewewe584a-7aad-5383-9774784564-0erewrewr455745138fa241c9b\",\n" +
                "  \"TopicArn\" : \"test:topic-cache\",\n" +
                "  \"Message\" : \"{\\\"offerId\\\": 10}\",\n" +
                "  \"Timestamp\" : \"2019-07-21T20:06:33.824Z\",\n" +
                "  \"SignatureVersion\" : \"1\",\n" +
                "  \"Signature\" : \"test.signature\",\n" +
                "  \"SigningCertURL\" : \"test.utl.cert.pem\",\n" +
                "  \"UnsubscribeURL\" : \"test.un-subscribe url\"\n" +
                "}";
        

        SqsMessage sqsMessage = new Gson().fromJson(str, SqsMessage.class);

        System.out.println(sqsMessage);

    }
}
