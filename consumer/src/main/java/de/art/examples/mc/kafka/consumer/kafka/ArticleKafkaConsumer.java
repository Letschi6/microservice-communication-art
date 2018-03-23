package de.art.examples.mc.kafka.consumer.kafka;

import de.art.examples.mc.kafka.consumer.Main;
import de.art.examples.mc.kafka.consumer.domain.Stock;
import de.art.examples.mc.kafka.consumer.domain.StockAvro;
import de.art.examples.mc.kafka.consumer.repository.StockRepository;
import de.art.examples.mc.kafka.producer.domain.ArticleAvro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.KafkaNull;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@KafkaListener(topics = "${kafka.article.topic.id}", groupId = "stock-consumer-article-group")
public class ArticleKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private final StockRepository stockRepository;
    private final KafkaTemplate<String, StockAvro> kafkaTemplate;
    @Value("${kafka.stock.topic.id}")
    private String stockTopic;

    @Autowired
    public ArticleKafkaConsumer(StockRepository stockRepository, KafkaTemplate<String, StockAvro> kafkaTemplate) {
        this.stockRepository = stockRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaHandler
    public void listen(@Payload(required = false) ArticleAvro article, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        Stock stock = stockRepository.findOne(key);
        if (stock == null) {
            log.warn("New article detected, add new stock: " + key + " " + article.getName());
            final StockAvro stockAvro = StockAvro.newBuilder().setId(key).setAmount(BigDecimal.valueOf(0)).build();
            kafkaTemplate.send(stockTopic, key, stockAvro);
        }
    }


    @KafkaHandler
    public void delete(@Payload(required = false) KafkaNull nul, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        final Stock stock = stockRepository.findOne(key);
        if (stock != null) {
            log.warn("Delete stock by article deletion: " + key);
            kafkaTemplate.send(stockTopic, key, null);
        }
    }
}
