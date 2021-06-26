package com.nagp.amcart.order.exception;

import lombok.Getter;

@Getter
public abstract class AmCartBaseException extends RuntimeException {

    private static final long serialVersionUID = -478376748646484516L;

    String errorCode;

    public AmCartBaseException(String message) {
        super(message);
    }

    public AmCartBaseException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
