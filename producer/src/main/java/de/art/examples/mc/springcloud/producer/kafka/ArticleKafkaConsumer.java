package de.art.examples.mc.springcloud.producer.kafka;

import de.art.examples.mc.springcloud.producer.domain.Article;
import de.art.examples.mc.springcloud.producer.repository.ArticleRepository;
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
@KafkaListener(topics = "${kafka.article.topic.id}")
public class ArticleKafkaConsumer {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleKafkaConsumer(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @KafkaHandler
    public void listen(@Payload Article article) {
        articleRepository.save(article);
    }


    @KafkaHandler
    public void delete(@Payload(required = false) KafkaNull nul, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        articleRepository.delete(UUID.fromString(key));
    }
}
