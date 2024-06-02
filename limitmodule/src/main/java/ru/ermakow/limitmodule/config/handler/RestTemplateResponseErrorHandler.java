package ru.ermakow.limitmodule.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import ru.ermakow.dto.response.PaymentStatusResponse;
import ru.ermakow.enums.ErrorCode;
import ru.ermakow.limitmodule.exception.WrongBalanceException;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatusCode statusCode = response.getStatusCode();
        return statusCode.is4xxClientError() || statusCode.is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError()) {
            ObjectMapper objectMapper = new ObjectMapper();
            PaymentStatusResponse paymentResponse = objectMapper.readValue(response.getBody(), PaymentStatusResponse.class);

            if (paymentResponse.errorCode().equals(ErrorCode.WRONG_BALANCE.toString())) {
                throw new WrongBalanceException("Недостаточно средств для осуществления платежа");
            }
            throw new RuntimeException("Something went wrong");
        }
    }
}
