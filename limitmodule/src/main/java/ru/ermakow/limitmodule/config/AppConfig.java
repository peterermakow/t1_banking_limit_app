package ru.ermakow.limitmodule.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.ermakow.limitmodule.config.handler.RestTemplateResponseErrorHandler;
import ru.ermakow.limitmodule.config.properties.ExecutorProperties;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate paymentIntegrationExecutor(ExecutorProperties properties, RestTemplateResponseErrorHandler errorHandler) {
        return new RestTemplateBuilder()
                .rootUri(properties.getRestTemplateProperties().getUrl())
                .setConnectTimeout(properties.getRestTemplateProperties().getConnectTimeout())
                .setReadTimeout(properties.getRestTemplateProperties().getReadTimeout())
                .errorHandler(errorHandler)
                .build();
    }
}
