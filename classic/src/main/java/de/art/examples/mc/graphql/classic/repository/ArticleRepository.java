package de.art.examples.mc.graphql.classic.repository;

import de.art.examples.mc.graphql.classic.domain.Article;
import de.art.examples.mc.graphql.classic.projection.SparseArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ArticleRepository extends JpaRepository<Article, String> {
    Set<SparseArticle> findAllBy();
}
