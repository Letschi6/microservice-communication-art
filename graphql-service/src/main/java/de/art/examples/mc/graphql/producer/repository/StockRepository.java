package de.art.examples.mc.graphql.producer.repository;

import de.art.examples.mc.graphql.producer.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
}
