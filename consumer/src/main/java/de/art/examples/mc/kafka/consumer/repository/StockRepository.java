package de.art.examples.mc.kafka.consumer.repository;

import de.art.examples.mc.kafka.consumer.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, UUID> {
    void deleteByUuid(UUID uuid);

    void deleteByArticleUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}
