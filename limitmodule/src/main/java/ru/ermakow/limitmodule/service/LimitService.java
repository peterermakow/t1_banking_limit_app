package ru.ermakow.limitmodule.service;

import org.springframework.http.ResponseEntity;
import ru.ermakow.dto.request.LimitRequest;
import ru.ermakow.dto.response.LimitResponse;

public interface LimitService {

    ResponseEntity<LimitResponse> decreaseLimit(LimitRequest request);

    ResponseEntity<LimitResponse> increaseLimit(LimitRequest request);
}
