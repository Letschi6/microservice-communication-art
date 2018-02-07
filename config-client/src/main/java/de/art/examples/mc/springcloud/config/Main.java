package de.art.examples.mc.springcloud.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by redmann on 08.04.16.
 */

@SpringBootApplication()
@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("Starting spring cloud config client");
        SpringApplication.run(Main.class, args);
    }
}
