package com.task_haibazo.exception;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class PageInvalid extends RuntimeException{
	
	private ErrorCode errorCode;
	
	public PageInvalid(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
	
}
