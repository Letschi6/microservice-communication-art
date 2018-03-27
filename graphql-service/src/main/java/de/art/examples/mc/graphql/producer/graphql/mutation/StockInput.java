package de.art.examples.mc.graphql.producer.graphql.mutation;

import java.math.BigDecimal;
import java.util.UUID;

public class StockInput {
    private String articleId = UUID.randomUUID().toString();
    private BigDecimal amount = BigDecimal.valueOf(0);

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
