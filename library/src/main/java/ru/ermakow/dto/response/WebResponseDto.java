package ru.ermakow.dto.response;

import lombok.Builder;

@Builder
public record WebResponseDto(
        boolean success,
        PaymentStatusResponse response
) {
}
