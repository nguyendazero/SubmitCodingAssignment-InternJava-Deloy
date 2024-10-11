package com.task_haibazo.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.ColorResponse;
import com.task_haibazo.dto.response.ProductDetailResponse;
import com.task_haibazo.dto.response.ProductImageResponse;
import com.task_haibazo.dto.response.ProductResponse;
import com.task_haibazo.dto.response.SizeResponse;
import com.task_haibazo.entity.Product;
import com.task_haibazo.repository.ProductRepository;
import com.task_haibazo.service.ColorService;
import com.task_haibazo.service.ProductImageService;
import com.task_haibazo.service.ProductService;
import com.task_haibazo.service.SizeService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private ProductImageService productImageService;

    @Override
    public APICustomize<List<ProductResponse>> products(Long sizeId, Double minPrice, Double maxPrice, Long colorId,
            Long styleId, Long categoryId, String sortBy, String sortOrder, int page, int size) {
        List<Product> products = productRepository.findProducts(sizeId, minPrice, maxPrice, colorId, styleId,
                categoryId, sortBy, sortOrder);
        // Tính toán xử lý phân trang
        int start = page * size;
        int end = Math.min(start + size, products.size());

        if (start >= products.size() || start < 0) {
            return new APICustomize<>(HttpStatus.NOT_FOUND.value(), "No products found!", Collections.emptyList());
        }

        List<Product> pagedProducts = products.subList(start, end);

        List<ProductResponse> productResponses = pagedProducts.stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getProductName(),
                        product.getPrice(),
                        product.getDiscount(),
                        product.getImage(),
                        product.getAverageStars()))
                .collect(Collectors.toList());

        String message = products.isEmpty() ? "No products found!" : "Products retrieved successfully!";
        int statusCode = products.isEmpty() ? HttpStatus.NOT_FOUND.value() : HttpStatus.OK.value();
        // Tạo API chuẩn với statuCode, message, result
        return new APICustomize<>(statusCode, message, productResponses);
    }

    @Override
    public APICustomize<ProductDetailResponse> product(long id) {

        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            List<SizeResponse> sizes = sizeService.sizes().getResult();
            List<ColorResponse> colors = colorService.colors().getResult();
            List<ProductImageResponse> productImages = productImageService.productImagesByProductId(product.getId())
                    .getResult();

            ProductDetailResponse productDetail = new ProductDetailResponse(
                    product.getId(),
                    product.getDescription(),
                    product.getTotalView(),
                    product.getProductName(),
                    product.getPrice(),
                    product.getDiscount(),
                    product.getImage(),
                    product.getSaleEndDate(),
                    product.getAverageStars(),
                    sizes,
                    colors,
                    productImages);
            // Tạo API chuẩn với statuCode, message, result khi product hợp lệ
            return new APICustomize<>(HttpStatus.OK.value(), "Product detail retrieved successfully!", productDetail);
        } else {
            // Tạo API chuẩn với statuCode, message, result khi product không hợp lệ
            return new APICustomize<>(HttpStatus.NOT_FOUND.value(), "Product not found!", null);
        }
    }
}