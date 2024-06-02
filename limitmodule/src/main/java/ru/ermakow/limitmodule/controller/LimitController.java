package ru.ermakow.limitmodule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ermakow.dto.request.PaymentRequest;
import ru.ermakow.dto.response.WebResponseDto;
import ru.ermakow.limitmodule.api.LimitApi;
import ru.ermakow.limitmodule.service.LimitService;

@RestController
@RequiredArgsConstructor
public class LimitController implements LimitApi {

    private final LimitService limitService;

    @Override
    public ResponseEntity<WebResponseDto> processLimitCheck(PaymentRequest paymentRequest) {
        return limitService.process(paymentRequest);
    }
}
