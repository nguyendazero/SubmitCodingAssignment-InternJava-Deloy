package com.task_haibazo.exception;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<APICustomize<String>> handleProductNotFoundException(ProductNotFoundException ex) {
        //Xử lý ProductNotFound
    	APICustomize<String> response = new APICustomize<>(ApiError.NOT_FOUND.getCode(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    @ExceptionHandler(ListProductEmptyException.class)
    public ResponseEntity<APICustomize<String>> handleListProductEmptyException(ListProductEmptyException ex) {
        //Xử lý List Product Empty
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
