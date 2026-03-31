package com.appsdeveloperblog.ws.products.service;

import com.appsdeveloperblog.ws.products.model.CreateProductModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    @Override
    public String createProduct(CreateProductModel createProductModel) {
        String productId = java.util.UUID.randomUUID().toString();
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(
                productId,
                createProductModel.getTitle(),
                createProductModel.getPrice(),
                createProductModel.getQuantity());

        // Publish the event to Kafka Asynchronously
        CompletableFuture<SendResult<String, ProductCreatedEvent>> future = kafkaTemplate.
                send("product-created-event", productId, productCreatedEvent);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                // Handle the exception
                log.error("Failed to send product created event: " + ex.getMessage());
            } else {
                // Optionally handle the successful send result
                log.info("Product created event sent successfully for product ID: " + productId);
            }
        });

        //use future.join() - to blok the thread and wait till above topic gets published
        // then this will be synchronous and will return the productId after topic gets published

        return productId;
    }
}
