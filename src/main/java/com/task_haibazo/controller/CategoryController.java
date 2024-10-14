package com.task_haibazo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task_haibazo.dto.request.CategoryRequest;
import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.CategoryResponse;
import com.task_haibazo.service.CategoryService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/categories")
@Validated
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("")
    public ResponseEntity<APICustomize<List<CategoryResponse>>> categories() {
        APICustomize<List<CategoryResponse>> response = categoryService.categories();
        return ResponseEntity.ok(response);
    }
	
	@PostMapping("")
    public ResponseEntity<APICustomize<CategoryResponse>> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        APICustomize<CategoryResponse> response = categoryService.save(categoryRequest);
        return ResponseEntity.ok(response);
    }
}
