package com.task_haibazo.service;

import java.util.List;

import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.ProductDetailResponse;
import com.task_haibazo.dto.response.ProductResponse;

public interface ProductService {
	
	public APICustomize<List<ProductResponse>> products(Long sizeId, Double minPrice, Double maxPrice, Long colorId, Long styleId, Long categoryId, String sortBy, String sortOrder, int page, int size);

	public APICustomize<ProductDetailResponse> product(long id);
}
