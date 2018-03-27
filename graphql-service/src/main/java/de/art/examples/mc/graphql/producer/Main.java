package de.art.examples.mc.graphql.producer;

import com.coxautodev.graphql.tools.SchemaParser;
import de.art.examples.mc.graphql.producer.domain.Article;
import de.art.examples.mc.graphql.producer.domain.Stock;
import de.art.examples.mc.graphql.producer.repository.ArticleRepository;
import de.art.examples.mc.graphql.producer.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * Created by redmann on 08.04.16.
 */

@SpringBootApplication
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Starting spring cloud producer");
        SpringApplication.run(Main.class, args);
    }


    @Bean
    public CommandLineRunner demo(ArticleRepository articleRepository, StockRepository stockRepository) {
        return (args) -> {
            Article article = new Article();
            article.setName("article 1");
            article.setDescription("description of " + article.getName());
            article.setPrice(BigDecimal.valueOf(1.99));
            article.setEanList(new LinkedList<>());
            article.getEanList().add("1234567890123");
            article = articleRepository.save(article);

            Stock stock = new Stock();
            stock.setArticle(article);
            stock.setAmount(BigDecimal.valueOf(3));
            stockRepository.save(stock);
        };
    }
}
