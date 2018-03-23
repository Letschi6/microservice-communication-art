package de.art.examples.mc.kafka.producer.repository;

import de.art.examples.mc.kafka.producer.domain.Article;
import de.art.examples.mc.kafka.producer.projection.SparseArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ArticleRepository extends JpaRepository<Article, String> {
    Set<SparseArticle> findAllBy();
}
