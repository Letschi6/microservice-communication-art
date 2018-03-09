package de.art.examples.mc.springcloud.producer;

import de.art.examples.mc.springcloud.producer.domain.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * Created by redmann on 08.04.16.
 */

@SpringBootApplication
@EnableKafka
@EnableSwagger2
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Starting spring cloud producer");
        SpringApplication.run(Main.class, args);
    }
}
