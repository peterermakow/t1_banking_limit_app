package ru.ermakow.dto.request;

/**
 * Универсальная Дто, используется как для уменьшения лимита, так и для восстановления
 */

public record LimitRequest(
        String clientId,
        PaymentAmount amount
) {
}
