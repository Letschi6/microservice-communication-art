package de.art.examples.mc.springcloud.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.net.URI;

@Controller
public class RequestController {
    private static final Logger log = LoggerFactory.getLogger(RequestController.class);

    private final LoadBalancerClient loadBalancer;

    @Autowired
    public RequestController(LoadBalancerClient loadBalancer) {
        this.loadBalancer = loadBalancer;
    }

    @GetMapping("/operation")
    public @ResponseBody
    String getOperation() throws ScriptException {
        String function = getPart("eureka-client-operand") + " "
                + getPart("eureka-client-operator") + " "
                + getPart("eureka-client-operand");
        log.info("Function: " + function);
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String result = "" + engine.eval(function);
        log.info("Result: " + result);
        return function + " = " + result;
    }

    private String getPart(String service) {
        ServiceInstance instance = loadBalancer.choose(service);
        if (instance != null && instance.getUri() != null) {
            URI uri = instance.getUri();
            log.info("Request load balanced service: " + service + " with uri: " + uri.toString());
            return (new RestTemplate()).getForObject(uri, String.class);
        }
        return null;
    }
}
