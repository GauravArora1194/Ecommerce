package com.nagp.ecommerce.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagp.ecommerce.dto.ProductUpdatesDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Component
public class SendProductUpdates {
    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${ecom.sqs.product-updates.url}")
    private String searchServiceQueue;

    public String sendProduct(@RequestBody ProductUpdatesDto productUpdatesDto) {
        try {
            queueMessagingTemplate.send(
                    searchServiceQueue,
                    MessageBuilder
                            .withPayload(new ObjectMapper().writeValueAsString(productUpdatesDto))
                            .build()
            );
        } catch (JsonProcessingException e) {
            log.error("Problem parsing payload", e);
        }
        return "{result : success}";
    }
}
