package de.art.examples.mc.springcloud.turbine;

import com.netflix.discovery.EurekaClient;
import com.netflix.turbine.discovery.InstanceDiscovery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.netflix.turbine.TurbineProperties;
import org.springframework.context.annotation.Bean;

/**
 * Created by redmann on 08.04.16.
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard
@EnableTurbine
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    @Autowired
    private DiscoveryClient discoveryClient;


    @Bean
    public InstanceDiscovery instanceDiscovery(TurbineProperties turbineProperties, EurekaClient eurekaClient) {
        return new OwnCommonsInstanceDiscovery(turbineProperties, eurekaClient, discoveryClient);
    }

    public static void main(String[] args) {
        log.info("Starting spring cloud turbine server");
        SpringApplication.run(Main.class, args);
    }
}
