package com.appsdeveloperblog.ws.products.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    NewTopic createProductCreatedEventTopic(){
        return TopicBuilder.name("product-created-event")
                .partitions(3)
                .build();
    }
}
