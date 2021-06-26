package com.nagp.amcart.order.web.rest.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nagp.amcart.order.dto.ErrorResponse;
import com.nagp.amcart.order.exception.AmCartBusinessException;
import com.nagp.amcart.order.exception.AmCartTechnicalException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(AmCartBusinessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleSecurityException(AmCartBusinessException ex) {
        return new ErrorResponse(ex);
    }

    @ExceptionHandler(AmCartTechnicalException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleSecurityException(AmCartTechnicalException ex) {
        return new ErrorResponse(ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}