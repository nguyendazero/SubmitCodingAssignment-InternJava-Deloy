package com.task_haibazo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

	 private String message; 
	 private String details; 
	
}
