package de.art.examples.mc.graphql.classic.controller;

import de.art.examples.mc.graphql.classic.Main;
import de.art.examples.mc.graphql.classic.domain.Article;
import de.art.examples.mc.graphql.classic.projection.SparseArticle;
import de.art.examples.mc.graphql.classic.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "rest/article")
public class RestArticleController {
    private static final Logger log = LoggerFactory.getLogger(Main.class);


    private final ArticleRepository articleRepository;

    @Autowired
    public RestArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @GetMapping("/list")
    public Set<SparseArticle> getArticleList() {
        return articleRepository.findAllBy();
    }


    @GetMapping("/{id}")
    public Article getArticleList(@PathVariable String id) {
        return articleRepository.findOne(id);
    }

    @PostMapping()
    public void updateArticle(@RequestBody Article article) {
        articleRepository.save(article);
    }

    @DeleteMapping("/{id}")
    public void removeArticle(@PathVariable String id) {
        articleRepository.delete(id);
    }
}
