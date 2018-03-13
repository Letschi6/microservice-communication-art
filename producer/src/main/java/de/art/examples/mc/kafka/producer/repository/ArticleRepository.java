package de.art.examples.mc.kafka.producer.repository;

import de.art.examples.mc.kafka.producer.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArticleRepository extends JpaRepository<Article, UUID> {
    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}
