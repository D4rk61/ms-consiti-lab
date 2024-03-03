package dev.jose.mssqsspring1.domain.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jose.mssqsspring1.domain.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Log4j2
@RequiredArgsConstructor
public class SQSService {

    @Value("${aws.queueName}")
    private String queueName;

    private final AmazonSQS amazonSQSClient;
    private final ObjectMapper objectMapper;

    public void publishMessage(ProductDTO productDTO) {
        try {
            GetQueueUrlResult queueUrlResult = amazonSQSClient.getQueueUrl(queueName);
            var product = ProductDTO.builder()
                    .id(productDTO.getId())
                    .name(productDTO.getName())
                    .price(productDTO.getPrice())
                    .build();

            var result =  amazonSQSClient.sendMessage(queueUrlResult.getQueueUrl(), objectMapper.writeValueAsString(product));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
