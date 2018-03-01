package de.art.examples.mc.springcloud.client.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "eureka-client-operator", fallback = OperatorFeignFallback.class)
public interface OperatorFeignInterface {
    @GetMapping("/")
    @HystrixCommand(commandKey = "key", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds"
                    , value = "30000")})
    String getOperation();
}

@Component
class OperatorFeignFallback implements OperatorFeignInterface {
    @Override
    public String getOperation() {
        return "+";
    }
}
