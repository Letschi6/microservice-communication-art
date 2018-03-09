package de.art.examples.mc.kafka.consumer.controller;

import de.art.examples.mc.kafka.consumer.repository.ArticleRepository;
import de.art.examples.mc.kafka.consumer.domain.Stock;
import de.art.examples.mc.kafka.consumer.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "rest/stock")
public class RestStockController {
    @Value("${kafka.stock.topic.id}")
    private String kafkaStockTopicId;

    private final StockRepository stockRepository;
    private final ArticleRepository articleRepository;
    private final KafkaTemplate<String, Stock> kafkaTemplate;

    @Autowired
    public RestStockController(StockRepository stockRepository, ArticleRepository articleRepository, KafkaTemplate<String, Stock> kafkaTemplate) {
        this.stockRepository = stockRepository;
        this.articleRepository = articleRepository;
        this.kafkaTemplate = kafkaTemplate;
    }


    @GetMapping("/list")
    public List<Stock> getStockList() {
        return stockRepository.findAll();
    }


    @GetMapping("/{uuid}")
    public Stock getStock(@PathVariable UUID uuid) {
        return stockRepository.findOne(uuid);
    }

    @PostMapping()
    public void addStock(@RequestBody Stock stock) {
        if (articleRepository.existsByUuid(stock.getArticle().getUuid())) {
            kafkaTemplate.send(kafkaStockTopicId, stock.getUuid().toString(), stock);
        }
    }

    @DeleteMapping("/{uuid}")
    public void removeStock(@PathVariable UUID uuid) {
        kafkaTemplate.send(kafkaStockTopicId, uuid.toString(), null);
    }
}
