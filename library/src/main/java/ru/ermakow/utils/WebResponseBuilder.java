package ru.ermakow.utils;

import ru.ermakow.dto.response.ErrorResponse;
import ru.ermakow.dto.response.PaymentStatusResponse;
import ru.ermakow.dto.response.WebResponseDto;

public class WebResponseBuilder {

    public static WebResponseDto createSuccessDto (PaymentStatusResponse response) {
        return new WebResponseDto(true, response, null);
    }

    public static WebResponseDto createFailureDto (ErrorResponse errorResponse) {
        return new WebResponseDto(false, null, errorResponse);
    }
}
