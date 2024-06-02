package ru.ermakow.limitmodule.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ermakow.limitmodule.model.LimitEntity;

import java.util.Map;

/**
 * Репозиторий используется в том случае, если приходит запрос на проверку лимита для ID клиента отсутствующего в базе
 * В случае достаточности денежных средств такой клиент будет записан в базу с актуальным(уменьшенным) дневным лимитом
 */

@Repository
@RequiredArgsConstructor
public class CustomLimitRepository {

    private final NamedParameterJdbcOperations jdbcOperations;

    private static final String CLIENT_ID_PARAM = "clientId";

    private static final String DEFAULT_DAY_LIMIT = "dayLimit";

    private static final String SAVE_AND_RETURN_SQL_QUERY
            = "INSERT INTO limits VALUES(cast(nextval('limits_id_seq') as bigint), :clientId, DEFAULT) RETURNING *";

    private static final String UPDATE_DAY_LIMIT_QUERY = "UPDATE limits SET day_limit = DEFAULT";

    @Scheduled(cron = "0 0 0 * * *")
    public void resetDayLimits() {
        Map<String, Object> params = Map.of(DEFAULT_DAY_LIMIT, new Object());
        jdbcOperations.update(UPDATE_DAY_LIMIT_QUERY, params);
    }

    @Transactional
    public LimitEntity saveAndReturn(Long clientId) {
        Map<String, Object> params = Map.of(CLIENT_ID_PARAM, clientId, DEFAULT_DAY_LIMIT, new Object());
        BeanPropertyRowMapper<LimitEntity> rowMapper = BeanPropertyRowMapper.newInstance(LimitEntity.class);
        return jdbcOperations.queryForObject(SAVE_AND_RETURN_SQL_QUERY, params, rowMapper);
    }

}
