package de.art.examples.mc.springcloud.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created by redmann on 08.04.16.
 */

@SpringBootApplication()
@EnableDiscoveryClient
@EnableFeignClients
@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Starting spring cloud feign client");
        SpringApplication.run(Main.class, args);
    }
}
