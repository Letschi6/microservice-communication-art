package de.art.examples.mc.springcloud.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Created by redmann on 08.04.16.
 */

@SpringBootApplication()
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrixDashboard
@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Starting spring cloud feign client");
        SpringApplication.run(Main.class, args);
    }
}
