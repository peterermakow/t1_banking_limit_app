package ru.ermakow.limitmodule.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.ermakow.dto.request.PaymentRequest;
import ru.ermakow.dto.response.PaymentStatusResponse;
import ru.ermakow.dto.response.WebResponseDto;
import ru.ermakow.limitmodule.exception.LimitExceedingException;
import ru.ermakow.limitmodule.repository.CustomLimitRepository;
import ru.ermakow.limitmodule.repository.LimitRepository;
import ru.ermakow.limitmodule.service.LimitService;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {

    private final RestTemplate restTemplate;
    private final LimitRepository limitRepository;
    private final CustomLimitRepository customLimitRepository;

    @Transactional(noRollbackFor = RuntimeException.class)
    public ResponseEntity<WebResponseDto> process(PaymentRequest paymentRequest) {

        Long clientId = Long.valueOf(paymentRequest.clientId());
        BigDecimal payment = new BigDecimal(paymentRequest.amount().value());
        var entity = limitRepository.findByClientId(clientId)
                .orElseGet(() -> customLimitRepository.saveAndReturn(clientId));

        if (entity.getDayLimit().compareTo(payment) < 0) {
            throw new LimitExceedingException("Превышен ежедневный лимит операций по продукту");
        }

        var response = restTemplate.postForEntity("/payments", paymentRequest, PaymentStatusResponse.class);
        entity.setDayLimit(entity.getDayLimit().subtract(payment));
        limitRepository.save(entity);
        return ResponseEntity.ok(WebResponseDto.builder().success(true).response(response.getBody()).build());
    }

}
