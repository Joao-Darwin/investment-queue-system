package com.producer.exceptions.handler;

import com.producer.exceptions.handler.exceptions.ResponseEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@ControllerAdvice
@RestController
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseEntityException> handlerAllExceptions(Exception exception, WebRequest webRequest) {
        ResponseEntityException responseEntityException = new ResponseEntityException(Instant.now(), exception.getMessage(), webRequest.getDescription(false));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseEntityException);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseEntityException> handlerIllegalArgumentException(Exception exception, WebRequest webRequest) {
        ResponseEntityException responseEntityException = new ResponseEntityException(Instant.now(), exception.getMessage(), webRequest.getDescription(false));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseEntityException);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseEntityException> handlerNullPointerException(Exception exception, WebRequest webRequest) {
        ResponseEntityException responseEntityException = new ResponseEntityException(Instant.now(), exception.getMessage(), webRequest.getDescription(false));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseEntityException);
    }
}
