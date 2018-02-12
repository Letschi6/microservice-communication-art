package de.art.examples.mc.springcloud.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class PrintConfiguration {
    @Value("${client.app}")
    private String clientApp;
    @Value("${client.mode}")
    private String clientMode;
    @Value("${client.settings.root}")
    private String root;
    @Value("${client.settings.general}")
    private String general;
    @Value("${client.settings.specific}")
    private String specific;

    @PostConstruct
    public void printConfiguration() {
        log.warn("--------------------");
        log.warn("App:\t\t" + clientApp);
        log.warn("Mode:\t\t" + clientMode);
        log.warn("Root:\t\t" + root);
        log.warn("General:\t" + general);
        log.warn("Specific:\t" + specific);
        log.warn("--------------------");
    }
}
