package de.art.examples.mc.graphql.producer.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import de.art.examples.mc.graphql.producer.domain.Article;
import de.art.examples.mc.graphql.producer.domain.Stock;
import de.art.examples.mc.graphql.producer.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockResolver implements GraphQLResolver<Stock> {
    private final ArticleRepository articleRepository;

    @Autowired
    public StockResolver(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article getArticle(Stock stock) {
        return articleRepository.findOne(stock.getArticle().getId());
    }
}
