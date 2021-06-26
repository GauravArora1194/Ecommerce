package com.nagp.amcart.order.exception;

import lombok.Getter;

@Getter
public class AmCartTechnicalException extends AmCartBaseException {

    private static final long serialVersionUID = -111652716956871024L;

    public AmCartTechnicalException(String message, String errorCode) {
        super(message, errorCode);
    }
}
