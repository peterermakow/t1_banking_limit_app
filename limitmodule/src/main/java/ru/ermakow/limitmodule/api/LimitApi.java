package ru.ermakow.limitmodule.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.ermakow.dto.request.LimitRequest;
import ru.ermakow.dto.response.LimitResponse;
import ru.ermakow.limitmodule.api.utils.UriConstants;

public interface LimitApi {

    @PostMapping(UriConstants.DECREASE_LIMIT)
    ResponseEntity<LimitResponse> decreaseLimit(@RequestBody LimitRequest request);

    @PostMapping(UriConstants.INCREASE_LIMIT)
    ResponseEntity<LimitResponse> increaseLimit(@RequestBody LimitRequest request);
}
