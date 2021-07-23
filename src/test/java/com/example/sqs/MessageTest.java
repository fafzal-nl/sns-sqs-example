package com.example.sqs;

import com.example.sqs.model.SqsMessage;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

public class MessageTest {


    @Test
    void testGson() {

        String str = "{\n" +
                "  \"Type\" : \"Notification\",\n" +
                "  \"MessageId\" : \"f06f584a-7aad-5383-9764-0138fa241c9b\",\n" +
                "  \"TopicArn\" : \"arn:aws:sns:us-east-1:934405900301:topic-cache\",\n" +
                "  \"Message\" : \"{\\\"offerId\\\": 10}\",\n" +
                "  \"Timestamp\" : \"2021-07-21T20:06:33.824Z\",\n" +
                "  \"SignatureVersion\" : \"1\",\n" +
                "  \"Signature\" : \"vGlhb4opo7nbVxX4V/pr4ny2jxR8RwyKlI89OMra1xlbRX0uTwde9TzApXGUL4k95BlZZdeVCcRxcpKUw0FdxX8ZaYFaM18vlRDM9bArOoD+/DvCvn+uFW9wOb0JVAYeRNDLZclZKyKZ61QbsxqXzqpl5JJCOZBIhSqLZWPRFONdXbndeeXT2sOyDcGGz5j1Z/bTPoxAp4iQ9CDw9sfmK4jtnUF0M8fKqZOUX2ZLfHMtgfmOK9eGlWILbf3nLWhJORDOOPY18gQP+stBrdOH8+mgiYEBLeKNagkEJh20blV3pLd41XdnBysCHisULAj7qJTvhK3/jVKYJIJ4GPr6zw==\",\n" +
                "  \"SigningCertURL\" : \"https://sns.us-east-1.amazonaws.com/SimpleNotificationService-010a507c1833636cd94bdb98bd93083a.pem\",\n" +
                "  \"UnsubscribeURL\" : \"https://sns.us-east-1.amazonaws.com/?Action=Unsubscribe&SubscriptionArn=arn:aws:sns:us-east-1:934405900301:topic-cache:948350f3-fad4-4a77-8e13-0f6ef5dee199\"\n" +
                "}";

        SqsMessage sqsMessage = new Gson().fromJson(str, SqsMessage.class);

        System.out.println(sqsMessage);

    }
}
