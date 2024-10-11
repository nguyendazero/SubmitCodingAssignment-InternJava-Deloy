package com.task_haibazo.exception;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class ProductNotFound extends RuntimeException{

	private ErrorCode errorCode;
	
	public ProductNotFound(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
