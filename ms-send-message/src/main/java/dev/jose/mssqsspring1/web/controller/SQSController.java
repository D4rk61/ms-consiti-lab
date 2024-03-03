package dev.jose.mssqsspring1.web.controller;

import dev.jose.mssqsspring1.domain.dto.ProductDTO;
import dev.jose.mssqsspring1.domain.service.SQSService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SQSController {

    private final SQSService sqsService;

    @PostMapping("/send-product")
    public void sendProductToQueue(@RequestBody ProductDTO productDTO) {

        sqsService.publishMessage(productDTO);
    }
}
