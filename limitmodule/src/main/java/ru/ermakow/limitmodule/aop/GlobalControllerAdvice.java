package ru.ermakow.limitmodule.aop;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.ermakow.dto.response.ErrorResponse;
import ru.ermakow.dto.response.WebResponseDto;
import ru.ermakow.limitmodule.exception.LimitExceedingException;
import ru.ermakow.limitmodule.exception.WrongBalanceException;
import ru.ermakow.utils.WebResponseBuilder;

import static ru.ermakow.enums.ErrorCode.*;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({LimitExceedingException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    WebResponseDto processLimitExceedingException(LimitExceedingException ex) {
        return WebResponseBuilder.createFailureDto(new ErrorResponse(LIMIT_EXCEEDED.toString(), ex.getMessage()));
    }

    @ExceptionHandler(WrongBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    WebResponseDto processWrongBalanceException(WrongBalanceException ex) {
        return WebResponseBuilder.createFailureDto(new ErrorResponse(WRONG_BALANCE.toString(), ex.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    WebResponseDto processRunTimeException(RuntimeException ex) {
        return WebResponseBuilder.createFailureDto(new ErrorResponse(UNDEFINED_ERROR.toString(), ex.getMessage()));
    }
}
