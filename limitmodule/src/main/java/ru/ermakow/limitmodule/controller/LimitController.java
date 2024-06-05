package ru.ermakow.limitmodule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.ermakow.dto.request.LimitRequest;
import ru.ermakow.dto.response.LimitResponse;
import ru.ermakow.limitmodule.api.LimitApi;
import ru.ermakow.limitmodule.service.LimitService;

@RestController
@RequiredArgsConstructor
public class LimitController implements LimitApi {

    private final LimitService limitService;

    @Override
    public ResponseEntity<LimitResponse> decreaseLimit(LimitRequest request) {
        return limitService.decreaseLimit(request);
    }

    @Override
    public ResponseEntity<LimitResponse> increaseLimit(LimitRequest request) {
        return limitService.increaseLimit(request);
    }

}
