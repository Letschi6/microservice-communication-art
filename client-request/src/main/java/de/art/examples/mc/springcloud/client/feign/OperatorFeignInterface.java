package de.art.examples.mc.springcloud.client.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "EUREKA-CLIENT-OPERATOR", fallback = OperatorFeignFallback.class)
public interface OperatorFeignInterface {
    @GetMapping("/")
    String getOperation();
}

@Component
class OperatorFeignFallback implements OperatorFeignInterface {
    @Override
    public String getOperation() {
        return "+";
    }
}
