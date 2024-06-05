package ru.ermakow.limitmodule.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ermakow.dto.request.LimitRequest;
import ru.ermakow.dto.response.LimitResponse;
import ru.ermakow.limitmodule.exception.LimitExceedingException;
import ru.ermakow.limitmodule.repository.CustomLimitRepository;
import ru.ermakow.limitmodule.repository.LimitRepository;
import ru.ermakow.limitmodule.service.LimitService;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {

    private final LimitRepository limitRepository;
    private final CustomLimitRepository customLimitRepository;

    @Transactional
    @Override
    public ResponseEntity<LimitResponse> decreaseLimit(LimitRequest request) {
        Long clientId = Long.valueOf(request.clientId());
        BigDecimal value = new BigDecimal(request.amount().value());
        var entity = limitRepository.findByClientId(clientId)
                .orElseGet(() -> customLimitRepository.saveAndReturn(clientId));

        if (entity.getDayLimit().compareTo(value) < 0) {
            throw new LimitExceedingException(String.format("Превышен ежедневный лимит операций по продукту для клиента с ID %d", clientId));
        }

        entity.setDayLimit(entity.getDayLimit().subtract(value));
        limitRepository.save(entity);
        return ResponseEntity.ok(new LimitResponse(String.format("Клиенту с ID %d уменьшен суточный лимит на %s %s", clientId, value, request.amount().currency())));
    }

    @Transactional
    @Override
    public ResponseEntity<LimitResponse> increaseLimit(LimitRequest request) {
        Long clientId = Long.valueOf(request.clientId());
        BigDecimal value = new BigDecimal(request.amount().value());
        var entity = limitRepository.findByClientId(clientId)
                .orElseGet(() -> customLimitRepository.saveAndReturn(clientId));

        entity.setDayLimit(entity.getDayLimit().add(value));
        limitRepository.save(entity);
        return ResponseEntity.ok(new LimitResponse(String.format("Клиенту с ID %d восстановлен суточный лимит на %s %s", clientId, value, request.amount().currency())));
    }

}
