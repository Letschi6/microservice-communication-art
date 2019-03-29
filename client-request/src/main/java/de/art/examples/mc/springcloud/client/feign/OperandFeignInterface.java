package de.art.examples.mc.springcloud.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("eureka-client-operand")
public interface OperandFeignInterface {
    @GetMapping("/")
    //@RequestMapping(value = "/", method = RequestMethod.GET)
    String getOperand();
}
