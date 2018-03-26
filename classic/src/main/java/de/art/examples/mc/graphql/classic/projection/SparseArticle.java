package de.art.examples.mc.graphql.classic.projection;

import de.art.examples.mc.graphql.classic.domain.Article;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name = "SparseArticle", types = {Article.class})
public interface SparseArticle {
    public String getId();

    public String getName();

    public String getDescription();

    public BigDecimal getPrice();
}
