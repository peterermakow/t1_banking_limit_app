package ru.ermakow.limitmodule.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@Getter
@Setter
@ConfigurationProperties(prefix = "limits.properties")
public class LimitProperties {

    private BigDecimal limit;
}
