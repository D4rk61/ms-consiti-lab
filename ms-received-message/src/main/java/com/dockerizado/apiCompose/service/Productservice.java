package com.dockerizado.apiCompose.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.dockerizado.apiCompose.entity.Product;
import com.dockerizado.apiCompose.entity.ProductDTO;
import com.dockerizado.apiCompose.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class Productservice {

    @Value("${aws.queueName}")
    private String queueName;

    private final AmazonSQS amazonSQSClient;
    private final ProductRepository productRepository;

    public Productservice(AmazonSQS amazonSQSClient, ProductRepository productRepository) {
        this.amazonSQSClient = amazonSQSClient;
        this.productRepository = productRepository;
    }
    public void consumeMessages() {
        try {
            String queueUrl = amazonSQSClient.getQueueUrl(queueName).getQueueUrl();
            ReceiveMessageResult receiveMessageResult = amazonSQSClient.receiveMessage(queueUrl);
            List<Message> messages = receiveMessageResult.getMessages();

            if (!messages.isEmpty()) {
                for (com.amazonaws.services.sqs.model.Message message : messages) {
                    System.out.println(message.getBody());
                    Product product = convertMessageToProduct(message);
                    productRepository.save(product);
                    amazonSQSClient.deleteMessage(queueUrl, message.getReceiptHandle());
                }
            } else {
                System.out.println("No hay mensajes en la cola.");
            }
        } catch (Exception e) {
            System.out.println("Error al consumir mensajes: " + e.getMessage());
        }
    }

    private Product convertMessageToProduct(com.amazonaws.services.sqs.model.Message message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ProductDTO productDTO = objectMapper.readValue(message.getBody(), ProductDTO.class);
            return new Product(productDTO.getId(), productDTO.getName(), productDTO.getPrice());
        } catch (IOException e) {
            throw new RuntimeException("Error al convertir el mensaje a Product: " + e.getMessage());
        }
    }
}
