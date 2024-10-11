package com.task_haibazo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.ProductDetailResponse;
import com.task_haibazo.dto.response.ProductResponse;
import com.task_haibazo.exception.ErrorCode;
import com.task_haibazo.exception.ProductNotFound;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.task_haibazo.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
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

		HttpHeaders headers = new HttpHeaders();
		APICustomize<List<ProductResponse>> response = productService.products(sizeId, minPrice, maxPrice, colorId,
				styleId, categoryId, sortBy, sortOrder, page, size);
		if (response.getResult().isEmpty()) {
			throw new ProductNotFound(ErrorCode.PRODUCT_NOT_FOUND);
		}
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<APICustomize<ProductDetailResponse>> getProductById(@PathVariable long id) {

        APICustomize<ProductDetailResponse> response = productService.product(id);

        if (response.getResult() == null) {
            throw new ProductNotFound(ErrorCode.PRODUCT_NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(response);
    }
}