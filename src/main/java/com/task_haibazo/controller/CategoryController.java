package com.task_haibazo.controller;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.CategoryResponse;
import com.task_haibazo.service.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/")
    public ResponseEntity<APICustomize<List<CategoryResponse>>> categories() {
        APICustomize<List<CategoryResponse>> response = categoryService.categories();
        HttpHeaders headers = new HttpHeaders();

        if(response.getResult().isEmpty()) {
        	return new ResponseEntity<>(response, headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
