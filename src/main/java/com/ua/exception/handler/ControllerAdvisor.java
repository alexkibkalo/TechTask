package com.ua.exception.handler;

import com.ua.exception.common.ExecutionFailed;
import com.ua.exception.common.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotFoundException.class, ConstraintViolationException.class, ExecutionFailed.class})
    public ResponseEntity<Object> handleOfferNotFoundException(Exception exception) {
        return getObjectResponseEntity(exception.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Set<String>> errors = ex.getFieldErrors().stream()
                .collect(groupingBy(FieldError::getField, mapping(DefaultMessageSourceResolvable::getDefaultMessage, Collectors.toSet())));

        Map<String, Set<String>> globalErrors = ex.getGlobalErrors().stream().collect(groupingBy(
                        ObjectError::getObjectName,
                        mapping(ObjectError::getDefaultMessage, Collectors.toUnmodifiableSet())
                )
        );
        errors.putAll(globalErrors);
        return handleExceptionInternal(ex, errors, headers, status, request);
    }

    private ResponseEntity<Object> getObjectResponseEntity(String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", message);

        log.error(message);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
