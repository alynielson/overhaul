package com.testapp.one.rest.v1.exception;

import com.testapp.one.service.exception.DataConflictException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse handleDataConflictException(WebRequest request, DataConflictException ex) {
        return ErrorResponse.from(request, HttpStatus.CONFLICT, ex.getLocalizedMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleBadRequestException(WebRequest request, BadRequestException ex) {
        return ErrorResponse.from(request, HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        ErrorResponse response = ErrorResponse.from(request, HttpStatus.BAD_REQUEST, ex.getBindingResult().getAllErrors()
        .stream().map(error -> String.format("field %s %s", ((FieldError) error).getField(), error.getDefaultMessage()))
                .collect(Collectors.joining(", ")));
        return handleExceptionInternal(ex, response, headers, status, request);
    }


    @Getter
    @AllArgsConstructor
    public static class ErrorResponse {
        private int status;
        private String error;
        private String message;
        private String path;
        private ZonedDateTime timestamp;

        public static ErrorResponse from(WebRequest request, HttpStatus status, String message) {
            String path = request.getDescription(false);
            return new ErrorResponse(status.value(), status.getReasonPhrase(), message,
                    (path != null && path.length() > 4) ? path.substring(4) : "", ZonedDateTime.now());
        }
    }
}
