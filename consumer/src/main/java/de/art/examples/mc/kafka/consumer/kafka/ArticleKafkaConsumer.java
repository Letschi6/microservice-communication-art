package de.art.examples.mc.kafka.consumer.kafka;

import de.art.examples.mc.kafka.consumer.Main;
import de.art.examples.mc.kafka.consumer.domain.Article;
import de.art.examples.mc.kafka.consumer.domain.Stock;
import de.art.examples.mc.kafka.consumer.repository.ArticleRepository;
import de.art.examples.mc.kafka.consumer.repository.StockRepository;
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
import java.util.UUID;

@Service
@KafkaListener(topics = "${kafka.article.topic.id}")
public class ArticleKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private final ArticleRepository articleRepository;
    private final StockRepository stockRepository;
    @Value("${kafka.stock.topic.id}")
    private String kafkaStockTopicId;
    private final KafkaTemplate<String, Stock> kafkaTemplate;

    @Autowired
    public ArticleKafkaConsumer(ArticleRepository articleRepository, StockRepository stockRepository, KafkaTemplate<String, Stock> kafkaTemplate) {
        this.articleRepository = articleRepository;
        this.stockRepository = stockRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaHandler
    public void listen(@Payload Article article) {
        log.warn("Add article: " + article.getUuid());
        boolean newArticle = !articleRepository.existsByUuid(article.getUuid());
        Article savedArticle = articleRepository.save(article);
        if (newArticle) {

            log.warn("New article: " + article.getUuid());
            Stock stock = new Stock();
            stock.setArticle(savedArticle);
            stock.setAmount(BigDecimal.valueOf(0));
            kafkaTemplate.send(kafkaStockTopicId, stock.getUuid().toString(), stock);
        }
    }


    @KafkaHandler
    public void delete(@Payload(required = false) KafkaNull nul, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        log.warn("Delete article: " + key);
        stockRepository.delete(UUID.fromString(key));
        articleRepository.delete(UUID.fromString(key));
    }
}
