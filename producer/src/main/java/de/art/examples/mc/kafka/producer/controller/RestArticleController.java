package de.art.examples.mc.kafka.producer.controller;

import de.art.examples.mc.kafka.producer.Main;
import de.art.examples.mc.kafka.producer.domain.Article;
import de.art.examples.mc.kafka.producer.domain.ArticleAvro;
import de.art.examples.mc.kafka.producer.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "rest/article")
public class RestArticleController {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    @Value("${kafka.article.topic.id}")
    private String kafkaArticleTopicId;

    private final ArticleRepository articleRepository;
    private final KafkaTemplate<String, ArticleAvro> kafkaTemplate;

    @Autowired
    public RestArticleController(ArticleRepository articleRepository, KafkaTemplate<String, ArticleAvro> kafkaTemplate) {
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
        final ArticleAvro articleAvro = ArticleAvro.newBuilder().setId(article.getId()).setPrice(article.getPrice()).setName(article.getName()).setDescription(article.getDescription()).build();
        kafkaTemplate.send(kafkaArticleTopicId, article.getId(), articleAvro);
    }

    @DeleteMapping("/{id}")
    public void removeArticle(@PathVariable String id) {
        kafkaTemplate.send(kafkaArticleTopicId, id, null);
    }
}
