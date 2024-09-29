package com.task_haibazo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.ProductImageResponse;
import com.task_haibazo.entity.ProductImage;
import com.task_haibazo.repository.ProductImageRepository;
import com.task_haibazo.service.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService{

	@Autowired
    private ProductImageRepository productImageRepository;

    public APICustomize<List<ProductImageResponse>> productImagesByProductId(long productId) {
        List<ProductImage> productImages = productImageRepository.findByProductId(productId);
        List<ProductImageResponse> productImageResponseList = productImages.stream()
                .map(image -> new ProductImageResponse(image.getId(), image.getImageUrl()))
                .collect(Collectors.toList());

        String message = productImages.isEmpty() ? "No product images found!" : "Product images retrieved successfully!";

        return new APICustomize<>(message, productImageResponseList);
    }

}
