package com.nagp.amcart.order.dto;

import com.nagp.amcart.order.exception.AmCartBaseException;

import lombok.Data;

@Data
public class ErrorResponse {

    private String message;
    private String errorCode;

    public ErrorResponse(AmCartBaseException ex) {
        this.message = ex.getMessage();
        this.errorCode = ex.getErrorCode();
    }

}
