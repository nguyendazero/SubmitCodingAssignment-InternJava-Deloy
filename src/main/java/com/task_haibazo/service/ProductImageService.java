package com.task_haibazo.service;

import java.util.List;

import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.ProductImageResponse;

public interface ProductImageService {
	public APICustomize<List<ProductImageResponse>> productImagesByProductId(long productId); 
}
