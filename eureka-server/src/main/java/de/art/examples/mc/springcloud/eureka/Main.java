package de.art.examples.mc.springcloud.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by redmann on 08.04.16.
 */

@SpringBootApplication()
@EnableEurekaServer
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Starting spring cloud eureka server");
        SpringApplication.run(Main.class, args);
    }
}
