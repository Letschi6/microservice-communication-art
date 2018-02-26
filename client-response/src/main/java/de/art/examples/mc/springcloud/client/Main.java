package de.art.examples.mc.springcloud.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by redmann on 08.04.16.
 */

@SpringBootApplication()
@EnableDiscoveryClient
@EnableSwagger2
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Starting spring cloud response client");
        SpringApplication.run(Main.class, args);
    }
}
