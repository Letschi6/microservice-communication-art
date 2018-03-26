package de.art.examples.mc.graphql.classic.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
public class Article {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String description;
    private BigDecimal price;
    @ElementCollection
    private List<String> eanList;


    public Article() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getEanList() {
        return eanList;
    }

    public void setEanList(List<String> eanList) {
        this.eanList = eanList;
    }
}
