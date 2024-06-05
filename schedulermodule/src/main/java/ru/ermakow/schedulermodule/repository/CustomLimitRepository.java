package ru.ermakow.schedulermodule.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomLimitRepository {

    private final NamedParameterJdbcOperations jdbcOperations;

    private static final String DEFAULT_DAY_LIMIT = "dayLimit";

    private static final String UPDATE_DAY_LIMIT_QUERY = "UPDATE limits SET day_limit = DEFAULT";

    public void resetDayLimitAtMidnight() {
        log.info("Day limit updated at {}", LocalDateTime.now());
        Map<String, Object> params = Map.of(DEFAULT_DAY_LIMIT, new Object());
        jdbcOperations.update(UPDATE_DAY_LIMIT_QUERY, params);
    }

}
