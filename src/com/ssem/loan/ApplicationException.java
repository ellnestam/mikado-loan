package com.ssem.loan;


public class ApplicationException extends RuntimeException {

    public ApplicationException(String message, Exception e) {
        super(message, e);
    }

    private static final long serialVersionUID = 1L;

}
