package de.art.examples.mc.graphql.producer.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import de.art.examples.mc.graphql.producer.domain.Article;
import de.art.examples.mc.graphql.producer.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleResolver implements GraphQLResolver<Article> {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleResolver(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
}
