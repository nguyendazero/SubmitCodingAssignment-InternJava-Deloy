package com.task_haibazo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class APICustomize<T> {
    private  String message = "success";
    private T result ;
    
}

