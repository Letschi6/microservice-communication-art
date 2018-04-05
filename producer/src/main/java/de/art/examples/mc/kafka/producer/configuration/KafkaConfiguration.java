package de.art.examples.mc.kafka.producer.configuration;

import de.art.examples.mc.kafka.producer.Main;
import de.art.examples.mc.kafka.producer.domain.Article;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConfiguration {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private final KafkaProperties kafkaProperties;

    public KafkaConfiguration(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }


    @Bean
    public ConsumerFactory<String, Article> articleConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(), new StringDeserializer(), new JsonDeserializer<>(Article.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Article> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Article> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(articleConsumerFactory());
        return factory;
    }
}
