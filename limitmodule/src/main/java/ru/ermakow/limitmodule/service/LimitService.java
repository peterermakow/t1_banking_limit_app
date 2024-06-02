package ru.ermakow.limitmodule.service;

import org.springframework.http.ResponseEntity;
import ru.ermakow.dto.request.PaymentRequest;
import ru.ermakow.dto.response.WebResponseDto;

public interface LimitService {

    ResponseEntity<WebResponseDto> process(PaymentRequest paymentRequest);
}
