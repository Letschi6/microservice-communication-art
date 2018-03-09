package de.art.examples.mc.springcloud.producer.controller;

import de.art.examples.mc.springcloud.producer.domain.Article;
import de.art.examples.mc.springcloud.producer.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "rest/stock")
public class RestArticleController {
    @Value("${kafka.article.topic.id}")
    private String kafkaArticleTopicId;

    private final ArticleRepository articleRepository;
    private final KafkaTemplate<String, Article> kafkaTemplate;

    @Autowired
    public RestArticleController(ArticleRepository articleRepository, KafkaTemplate<String, Article> kafkaTemplate) {
        this.articleRepository = articleRepository;
        this.kafkaTemplate = kafkaTemplate;
    }


    @GetMapping("/list")
    public List<Article> getStockList() {
        return articleRepository.findAll();
    }


    @GetMapping("/{uuid}")
    public Article getStockList(@PathVariable UUID uuid) {
        return articleRepository.findOne(uuid);
    }

    @PostMapping()
    public void addArticle(@RequestBody Article article) {
        kafkaTemplate.send(kafkaArticleTopicId, article.getUuid().toString(), article);
    }

    @DeleteMapping("/{uuid}")
    public void removeStock(@PathVariable UUID uuid) {
        kafkaTemplate.send(kafkaArticleTopicId, uuid.toString(), null);
    }
}
