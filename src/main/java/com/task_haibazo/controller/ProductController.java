package com.task_haibazo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.task_haibazo.dto.request.ProductRequest;
import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.ProductDetailResponse;
import com.task_haibazo.dto.response.ProductResponse;
import com.task_haibazo.entity.Product;

import java.util.List;
import org.springframework.http.HttpStatus;
import com.task_haibazo.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
@Validated
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("")
	public ResponseEntity<APICustomize<List<ProductResponse>>> products(
	        @RequestParam(required = false) Long sizeId,
	        @RequestParam(required = false) Double minPrice,
	        @RequestParam(required = false) Double maxPrice,
	        @RequestParam(required = false) Long colorId,
	        @RequestParam(required = false) Long styleId,
	        @RequestParam(required = false) Long categoryId,
	        @RequestParam(required = false) String sortBy,
	        @RequestParam(required = false) String sortOrder,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size) {

	    APICustomize<List<ProductResponse>> response = productService.products(sizeId, minPrice, maxPrice, colorId,
	            styleId, categoryId, sortBy, sortOrder, page, size);
	    
	    if (response.getResult().isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }
	    return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<APICustomize<ProductDetailResponse>> product(@PathVariable long id) {

        APICustomize<ProductDetailResponse> response = productService.product(id);
        return ResponseEntity.ok(response);
    }
	
	@PostMapping
    public ResponseEntity<String> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        
		Product product = new Product();
		product.setProductName(productRequest.getProductName());

        return ResponseEntity.status(HttpStatus.CREATED).body("Sản phẩm hợp lệ và đã được tạo!");
    }
}