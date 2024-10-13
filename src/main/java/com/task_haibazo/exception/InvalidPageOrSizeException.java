package com.task_haibazo.exception;


@SuppressWarnings("serial")
public class InvalidPageOrSizeException extends RuntimeException {
    public InvalidPageOrSizeException(String message) {
        super(message);
    }
}
