package com.task_haibazo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.task_haibazo.dto.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
    @ExceptionHandler(ProductNotFound.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFound ex) {
        ErrorResponse errorResponse = new ErrorResponse(
            ex.getErrorCode().getMessage(), 
            "Error Code: " + ex.getErrorCode().getCode() 
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handlePageInvalid(PageInvalid pi) {
        ErrorResponse errorResponse = new ErrorResponse(
            pi.getErrorCode().getMessage(), 
            "Error Code: " + pi.getErrorCode().getCode() 
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        // Tạo ErrorResponse cho các lỗi khác
        ErrorResponse errorResponse = new ErrorResponse(
            "An unexpected error occurred", // Thông điệp lỗi chung
            ex.getMessage() // Chi tiết lỗi
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
