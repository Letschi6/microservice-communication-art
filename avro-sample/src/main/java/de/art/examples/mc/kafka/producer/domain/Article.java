package de.art.examples.mc.kafka.producer.domain;

import org.apache.avro.reflect.AvroMeta;
import org.apache.avro.reflect.Nullable;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Article {
    private String id = UUID.randomUUID().toString();
    /**
     * Name of the Article
     */
    private String name;
    @Nullable
    private String description;
    private BigDecimal price;
    private List<EAN> eanList;


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

    public List<EAN> getEanList() {
        return eanList;
    }

    public void setEanList(List<EAN> eanList) {
        this.eanList = eanList;
    }
}
