package com.bca.vehicleloan.exception;

public class InputFormatException extends RuntimeException {
    public InputFormatException(String errorMessage) {
        super(errorMessage);
    }
}
