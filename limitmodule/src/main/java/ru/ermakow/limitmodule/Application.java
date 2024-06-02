package ru.ermakow.limitmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.ermakow.limitmodule.config.properties.ExecutorProperties;
import ru.ermakow.limitmodule.config.properties.LimitProperties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({
        ExecutorProperties.class,
        LimitProperties.class
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
