package de.art.examples.mc.kafka.consumer.repository;

import de.art.examples.mc.kafka.consumer.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
}
