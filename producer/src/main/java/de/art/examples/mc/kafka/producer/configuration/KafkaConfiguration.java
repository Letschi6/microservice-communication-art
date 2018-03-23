package de.art.examples.mc.kafka.producer.configuration;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {
    public KafkaConfiguration(KafkaProperties kafkaProperties) {
        kafkaProperties.getProperties().put("schema.registry.url", "http://127.0.0.1:8081");
        kafkaProperties.getProperties().put("specific.avro.reader", "true");
    }
}
