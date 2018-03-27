package de.art.examples.mc.graphql.producer.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Stock {
    @Id
    private String id = UUID.randomUUID().toString();
    private BigDecimal amount = BigDecimal.valueOf(0);
    @OneToOne(optional = false, cascade = CascadeType.REMOVE)
    private Article article;


    public Stock() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
