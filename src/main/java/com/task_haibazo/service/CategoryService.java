package com.task_haibazo.service;

import java.util.List;

import com.task_haibazo.dto.request.CategoryRequest;
import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.CategoryResponse;

public interface CategoryService {
	
	 public APICustomize<List<CategoryResponse>> categories() ; 
	 
	 public APICustomize<CategoryResponse> save(CategoryRequest categoryRequest);
}
