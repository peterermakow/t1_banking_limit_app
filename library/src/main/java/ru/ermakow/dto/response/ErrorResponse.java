package ru.ermakow.dto.response;

public record ErrorResponse(
        String code,
        String message
) {
}
