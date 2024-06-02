package ru.ermakow.limitmodule.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ermakow.dto.request.PaymentRequest;
import ru.ermakow.dto.response.WebResponseDto;

@RequestMapping("/limit")
public interface LimitApi {

    @PostMapping
    ResponseEntity<WebResponseDto> processLimitCheck(@RequestBody PaymentRequest paymentRequest);
}
