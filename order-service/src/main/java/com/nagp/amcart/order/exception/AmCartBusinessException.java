package com.nagp.amcart.order.exception;

import lombok.Getter;

@Getter
public class AmCartBusinessException extends AmCartBaseException {

    private static final long serialVersionUID = 3357603599330925502L;

    public AmCartBusinessException(String message, String errorCode) {
        super(message, errorCode);
    }

}
