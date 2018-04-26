package de.art.examples.mc.graphql.classic.projection;

import de.art.examples.mc.graphql.classic.domain.Article;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "SparseArticle", types = {Article.class})
public interface SparseArticle {
    String getId();

    String getName();

    String getDescription();

    BigDecimal getPrice();
}
