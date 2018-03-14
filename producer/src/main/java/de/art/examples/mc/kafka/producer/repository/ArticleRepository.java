package de.art.examples.mc.kafka.producer.repository;

import de.art.examples.mc.kafka.producer.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, String> {
}
