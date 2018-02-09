package de.art.examples.mc.springcloud.eureka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by redmann on 08.04.16.
 */

@SpringBootApplication()
@EnableEurekaServer
@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Starting spring cloud eureka server");
        SpringApplication.run(Main.class, args);
    }
}
