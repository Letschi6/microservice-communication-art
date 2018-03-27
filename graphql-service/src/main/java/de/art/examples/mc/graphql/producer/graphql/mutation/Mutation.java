package de.art.examples.mc.graphql.producer.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.art.examples.mc.graphql.producer.domain.Article;
import de.art.examples.mc.graphql.producer.domain.Stock;
import de.art.examples.mc.graphql.producer.repository.ArticleRepository;
import de.art.examples.mc.graphql.producer.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class Mutation implements GraphQLMutationResolver {

    private final ArticleRepository articleRepository;
    private final StockRepository stockRepository;

    public Mutation(ArticleRepository articleRepository, StockRepository stockRepository) {
        this.articleRepository = articleRepository;
        this.stockRepository = stockRepository;
    }

    public Article newArticle(String name, BigDecimal price, List<String> eanList) {
        Article article = new Article();
        article.setName(name);
        article.setPrice(price);
        article.setEanList(eanList);
        return articleRepository.save(article);
    }

    public Article updateArticle(String id, String name, BigDecimal price, List<String> eanList) {
        Article article = articleRepository.findOne(id);
        if (name != null) {
            article.setName(name);
        }
        if (price != null) {
            article.setPrice(price);
        }
        if (eanList != null) {
            article.setEanList(eanList);
        }
        return articleRepository.save(article);
    }

    public boolean deleteArticle(String id) {
        articleRepository.delete(id);
        return true;
    }


    public Stock newStockWithInput(StockInput stockInput) {
        Article article = articleRepository.findOne(stockInput.getArticleId());
        Stock stock = new Stock();
        stock.setArticle(article);
        stock.setAmount(stockInput.getAmount());
        return stockRepository.save(stock);
    }


    public Stock newStock(String articleId) {
        Article article = articleRepository.findOne(articleId);
        Stock stock = new Stock();
        stock.setArticle(article);
        return stockRepository.save(stock);
    }

    public Stock updateStock(String id, BigDecimal amount) {
        Stock stock = stockRepository.findOne(id);
        stock.setAmount(amount);
        return stockRepository.save(stock);
    }

    public boolean deleteStock(String id) {
        Stock stock = stockRepository.findOne(id);
        stockRepository.delete(stock);
        articleRepository.delete(stock.getArticle());
        return true;
    }

}
