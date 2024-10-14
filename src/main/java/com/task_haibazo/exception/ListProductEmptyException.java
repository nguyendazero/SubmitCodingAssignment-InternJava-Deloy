package com.task_haibazo.exception;

@SuppressWarnings("serial")
public class ListProductEmptyException extends RuntimeException {
    public ListProductEmptyException(String message) {
        super(message);
    }
}
