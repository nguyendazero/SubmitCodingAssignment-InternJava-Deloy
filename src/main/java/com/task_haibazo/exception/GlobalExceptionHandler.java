package com.task_haibazo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.ErrorResponse;
import com.task_haibazo.enums.ApiError;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        // Xử lý khi truyền lỗi các tham số
        ErrorResponse errorResponse = new ErrorResponse(
                "Invalid parameter: " + ex.getName(), 
                "Error Code: 400"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<APICustomize<String>> handleProductNotFoundException(ProductNotFoundException ex) {
        //Xử lý ProductNotFound
    	APICustomize<String> response = new APICustomize<>(ApiError.NOT_FOUND.getCode(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    @ExceptionHandler(InvalidPageOrSizeException.class)
    public ResponseEntity<APICustomize<String>> handleInvalidPageOrSizeException(InvalidPageOrSizeException ex) {
        //Xử lý ProductNotFound
    	APICustomize<String> response = new APICustomize<>(ApiError.BAD_REQUEST.getCode(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
    	//Xử lý các lỗi khác
        ErrorResponse errorResponse = new ErrorResponse(
            "An unexpected error occurred chung", 
            ex.getMessage() 
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
