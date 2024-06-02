package ru.ermakow.limitmodule.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "integrations.payments.client")
public class ExecutorProperties {

    private final RestTemplateProperties restTemplateProperties;

    public ExecutorProperties(RestTemplateProperties restTemplateProperties) {
        this.restTemplateProperties = restTemplateProperties;
    }
}
