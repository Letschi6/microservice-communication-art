package de.art.examples.mc.springcloud.producer.configuration;

import de.art.examples.mc.springcloud.producer.Main;
import de.art.examples.mc.springcloud.producer.domain.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Configuration
public class KafkaConfiguration {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private final ConsumerFactory defaultKafkaConsumerFactory;

    @Autowired
    public KafkaConfiguration(ConsumerFactory defaultKafkaConsumerFactory) {
        this.defaultKafkaConsumerFactory = defaultKafkaConsumerFactory;
    }

    //    @Bean
//    public Map<String, Object> producerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//        return props;
//    }
//
    @PostConstruct
    public void consumerFactory() {
        log.warn("Set DefaultKafkaConsumerFactory");
        ((DefaultKafkaConsumerFactory) defaultKafkaConsumerFactory).setValueDeserializer(new JsonDeserializer(Article.class));
    }

}
