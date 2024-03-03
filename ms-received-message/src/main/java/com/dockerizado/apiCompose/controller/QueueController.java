package com.dockerizado.apiCompose.controller;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.dockerizado.apiCompose.service.Productservice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueueController {
    private final AmazonSQS amazonSQSClient;

    private final Productservice productservice;

    public QueueController(AmazonSQS amazonSQSClient, Productservice productservice) {
        this.amazonSQSClient = amazonSQSClient;
        this.productservice = productservice;
    }

    @GetMapping("/save-message")
    public void getQueueMessageCount() {
        try {
            this.productservice.consumeMessages();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
