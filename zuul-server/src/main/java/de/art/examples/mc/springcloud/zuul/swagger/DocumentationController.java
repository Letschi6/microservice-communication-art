package de.art.examples.mc.springcloud.zuul.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Primary
@Slf4j
public class DocumentationController implements SwaggerResourcesProvider {
    private final DiscoveryClient discoveryClient;

    @Autowired
    public DocumentationController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public List<SwaggerResource> get() {
        final List<String> services = discoveryClient.getServices();
        Collections.sort(services);
        final List<SwaggerResource> resources = new ArrayList<SwaggerResource>();
        for (String service : services) {
            resources.add(swaggerResource(service, "/" + service + "/v2/api-docs"));
        }
        return resources;
    }


    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
