package de.art.examples.mc.kafka.producer.kafka;

import de.art.examples.mc.kafka.producer.domain.Article;
import de.art.examples.mc.kafka.producer.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.KafkaNull;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@KafkaListener(topics = "${kafka.article.topic.id}", containerFactory = "kafkaListenerContainerFactory")
public class ArticleKafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(ArticleKafkaConsumer.class);

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleKafkaConsumer(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @KafkaHandler
    public void listen(@Payload Article article) {

        log.info("Save article: " + article.getId());
        articleRepository.save(article);
    }


    @KafkaHandler
    public void delete(@Payload(required = false) KafkaNull nul, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        log.info("Delete article: " + key);
        articleRepository.delete(key);
    }
}
