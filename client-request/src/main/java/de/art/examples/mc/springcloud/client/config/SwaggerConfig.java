package de.art.examples.mc.springcloud.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Created by redmann on 07.03.17.
 */
@Configuration
public class SwaggerConfig
{
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2).groupName("default").select()
                .apis(RequestHandlerSelectors.basePackage("de.art.examles")).build();
    }
}
