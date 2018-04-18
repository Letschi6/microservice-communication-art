package de.art.examples.mc.graphql.producer.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import de.art.examples.mc.graphql.producer.domain.Article;
import de.art.examples.mc.graphql.producer.domain.Stock;
import de.art.examples.mc.graphql.producer.repository.ArticleRepository;
import de.art.examples.mc.graphql.producer.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class Query implements GraphQLQueryResolver {
    private final ArticleRepository articleRepository;
    private final StockRepository stockRepository;

    public Query(ArticleRepository articleRepository, StockRepository stockRepository) {
        this.articleRepository = articleRepository;
        this.stockRepository = stockRepository;
    }

    public Iterable<Article> findAllArticles() {
        return articleRepository.findAll();
    }

    public long countArticles() {
        return articleRepository.count();
    }

    public Iterable<Stock> findAllStocks() {
        return stockRepository.findAll();
    }

    public long countStocks() {
        return stockRepository.count();
    }

}
