package de.art.examples.mc.springcloud.client.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "EUREKA-CLIENT-OPERAND", fallback = OperandFeignFallback.class)
public interface OperandFeignInterface {
    @GetMapping("/")
    String getOperand();
}

@Component
class OperandFeignFallback implements OperandFeignInterface {
    @Override
    public String getOperand() {
        return "1";
    }
}