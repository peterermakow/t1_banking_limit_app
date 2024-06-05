package ru.ermakow.schedulermodule.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.ermakow.schedulermodule.repository.CustomLimitRepository;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulerConfig {

    private final CustomLimitRepository customLimitRepository;

    @Scheduled(cron = "${scheduler.cron-expression}")
    void resetLimitExecutor() {
        customLimitRepository.resetDayLimitAtMidnight();
    }
}
