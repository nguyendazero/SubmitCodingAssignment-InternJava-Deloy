package com.task_haibazo.exception;

public enum ErrorCode {
	
	PRODUCT_NOT_FOUND("404","Product Not Found"),
	INVALID_PAGE_PARAMETER("400", "Page parameter Is Invalid"),
    HTTP_REQUEST_ENDPOINT_SUPPORT("9999","BAD ENDPOINT");

	private String code = "9999";
    private String message ;
	
	ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
