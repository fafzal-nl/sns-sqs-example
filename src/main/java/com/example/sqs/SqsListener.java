package com.example.sqs;

import com.example.sqs.model.OfferMessage;
import com.example.sqs.model.SqsMessage;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Slf4j
public class SqsListener implements MessageListener {

    private final Gson gson = new Gson();

    @Override
    public void onMessage(Message message) {
        try {
            // Cast the received message as TextMessage and print the text to screen.
            String text = ((TextMessage) message).getText();

            System.out.println("Received: " + text);

            SqsMessage sqsMessage = gson.fromJson(text, SqsMessage.class);
            OfferMessage offerMessage = gson.fromJson(sqsMessage.message, OfferMessage.class);
            System.out.println("OfferId is " + offerMessage.getOfferId());

        } catch (JMSException e) {
            log.error("Failed to process a  message ", e);
        }
    }
}