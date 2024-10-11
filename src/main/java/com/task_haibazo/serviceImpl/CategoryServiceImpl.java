package com.task_haibazo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.CategoryResponse;
import com.task_haibazo.entity.Category;
import com.task_haibazo.enums.ApiError;
import com.task_haibazo.repository.CategoryRepository;

import com.task_haibazo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public APICustomize<List<CategoryResponse>> categories() {
	
	    List<Category> categories = categoryRepository.findAll();
	    
	    List<CategoryResponse> categoryResponseList = categories.stream()
	            .map(category -> new CategoryResponse(category.getId(), category.getCategorName()))
	            .collect(Collectors.toList());


	    if (categories.isEmpty()) {
	        return new APICustomize<>(ApiError.NOT_FOUND.getCode(), ApiError.NOT_FOUND.getMessage(), categoryResponseList);
	    }

	    return new APICustomize<>(ApiError.OK.getCode(), ApiError.OK.getMessage(), categoryResponseList);
	}

}
