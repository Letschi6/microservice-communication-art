package de.art.examples.mc.springcloud.zuul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by redmann on 08.04.16.
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@EnableZuulProxy
@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Starting spring cloud zuul server");
        SpringApplication.run(Main.class, args);
    }
}
