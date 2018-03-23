package de.art.examples.mc.kafka.consumer.controller;

import de.art.examples.mc.kafka.consumer.domain.Stock;
import de.art.examples.mc.kafka.consumer.domain.StockAvro;
import de.art.examples.mc.kafka.consumer.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "rest/stock")
public class RestStockController {
    @Value("${kafka.stock.topic.id}")
    private String kafkaStockTopicId;

    private final StockRepository stockRepository;
    private final KafkaTemplate<String, StockAvro> kafkaTemplate;

    @Autowired
    public RestStockController(StockRepository stockRepository, KafkaTemplate<String, StockAvro> kafkaTemplate) {
        this.stockRepository = stockRepository;
        this.kafkaTemplate = kafkaTemplate;
    }


    @GetMapping("/list")
    public List<Stock> getStockList() {
        return stockRepository.findAll();
    }


    @GetMapping("/{id}")
    public Stock getStock(@PathVariable String id) {
        return stockRepository.findOne(id);
    }

    @PostMapping()
    public void updateStock(@RequestBody Stock stock) {
        StockAvro stockAvro = StockAvro.newBuilder().setId(stock.getId()).setAmount(stock.getAmount()).build();
        kafkaTemplate.send(kafkaStockTopicId, stock.getId(), stockAvro);
    }
}
