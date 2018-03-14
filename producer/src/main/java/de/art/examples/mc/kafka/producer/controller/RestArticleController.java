package de.art.examples.mc.kafka.producer.controller;

import de.art.examples.mc.kafka.producer.domain.Article;
import de.art.examples.mc.kafka.producer.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "rest/article")
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
    public List<Article> getArticleList() {
        return articleRepository.findAll();
    }


    @GetMapping("/{id}")
    public Article getArticleList(@PathVariable String id) {
        return articleRepository.findOne(id);
    }

    @PostMapping()
    public void updateArticle(@RequestBody Article article) {
        kafkaTemplate.send(kafkaArticleTopicId, article.getId(), article);
    }

    @DeleteMapping("/{id}")
    public void removeArticle(@PathVariable String id) {
        kafkaTemplate.send(kafkaArticleTopicId, id, null);
    }
}
