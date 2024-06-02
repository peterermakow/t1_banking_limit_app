package ru.ermakow.dto.request;

/**
 * PaymentRequest передается по цепочке от микросервиса, запросившего платеж, через МС Проверки ежедневного лимита по платежам
 * в микросервис Платежей
 */

public record PaymentRequest(
        String id,
        String clientId,
        PaymentAmount amount
) {
}
