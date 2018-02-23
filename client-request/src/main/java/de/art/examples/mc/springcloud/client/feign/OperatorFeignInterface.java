package de.art.examples.mc.springcloud.client.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("EUREKA-CLIENT-OPERATOR")
public interface OperatorFeignInterface {
    @GetMapping("/")
    //@RequestMapping(value = "/", method = RequestMethod.GET)
    String getOperation();
}
