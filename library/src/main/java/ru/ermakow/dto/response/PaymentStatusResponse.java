package ru.ermakow.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.ermakow.dto.request.PaymentAmount;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentStatusResponse(
        String id,
        Boolean paid,
        String clientId,
        PaymentAmount amount,
        String errorCode
) {
}
