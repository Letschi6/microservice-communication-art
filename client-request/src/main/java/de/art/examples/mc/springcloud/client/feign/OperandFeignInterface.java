package de.art.examples.mc.springcloud.client.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("EUREKA-CLIENT-OPERAND")
public interface OperandFeignInterface {
    @GetMapping("/")
    String getOperand();
}
