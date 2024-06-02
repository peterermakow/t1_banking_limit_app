package ru.ermakow.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record WebResponseDto(
        boolean success,
        PaymentStatusResponse response,
        ErrorResponse error
) {
}
