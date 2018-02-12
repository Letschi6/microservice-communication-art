package de.art.examples.mc.springcloud.client.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@Profile("operator")
public class OperatorController {
    @GetMapping("/")
    public String getOperation() {
        final List<String> operationList = Arrays.asList("+", "-", "*", "/");
        int element = ThreadLocalRandom.current().nextInt(operationList.size());
        return operationList.get(element);
    }
}
