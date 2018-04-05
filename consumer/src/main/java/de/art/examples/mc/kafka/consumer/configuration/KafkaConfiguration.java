package de.art.examples.mc.kafka.consumer.configuration;

import de.art.examples.mc.kafka.consumer.domain.Article;
import de.art.examples.mc.kafka.consumer.domain.Stock;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConfiguration {
//    private final ConsumerFactory defaultKafkaConsumerFactory;


    private final KafkaProperties kafkaProperties;


    @Autowired
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


    @Bean
    public ConsumerFactory<String, Stock> stockConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(), new StringDeserializer(), new JsonDeserializer<>(Stock.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Stock> stockContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Stock> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stockConsumerFactory());
        return factory;
    }
}
