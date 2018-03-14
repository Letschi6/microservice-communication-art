package de.art.examples.mc.kafka.consumer.kafka;

import de.art.examples.mc.kafka.consumer.Main;
import de.art.examples.mc.kafka.consumer.domain.Stock;
import de.art.examples.mc.kafka.consumer.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.KafkaNull;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service()
@KafkaListener(topics = "${kafka.stock.topic.id}", containerFactory = "stockContainerFactory")
public class StockKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private final StockRepository stockRepository;

    @Autowired
    public StockKafkaConsumer(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @KafkaHandler
    public void listen(@Payload Stock stock) {
        log.warn("Update stock: " + stock.getId());
        stockRepository.save(stock);
    }


    @KafkaHandler
    public void delete(@Payload(required = false) KafkaNull nul, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        if (stockRepository.exists(key)) {
            log.warn("Delete stock: " + key);
            stockRepository.delete(key);
        }
    }
}
