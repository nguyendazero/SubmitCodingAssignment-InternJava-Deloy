package com.task_haibazo.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.ColorResponse;
import com.task_haibazo.dto.response.ProductDetailResponse;
import com.task_haibazo.dto.response.ProductImageResponse;
import com.task_haibazo.dto.response.ProductResponse;
import com.task_haibazo.dto.response.SizeResponse;
import com.task_haibazo.entity.Product;
import com.task_haibazo.enums.ApiError;
import com.task_haibazo.exception.InvalidPageOrSizeException;
import com.task_haibazo.exception.ListProductEmptyException;
import com.task_haibazo.exception.ProductNotFoundException;
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

        if (page < 0 || size <= 0) {
            throw (page < 0) 
                ? new InvalidPageOrSizeException("Page không được nhỏ hơn 0") 
                : new InvalidPageOrSizeException("Size không được nhỏ hơn hoặc bằng 0");
        }

        int start = page * size;
        int end = Math.min(start + size, products.size());


        if (products.isEmpty() || start >= products.size() || start < 0) {
        	throw new ListProductEmptyException("Danh sách rỗng, không tìm thấy sản phẩm nào");
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

        return new APICustomize<>(ApiError.OK.getCode(), ApiError.OK.getMessage(), productResponses);
    }


    @Override
    public APICustomize<ProductDetailResponse> product(long id) {

        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            List<SizeResponse> sizes = sizeService.sizes().getResult();
            List<ColorResponse> colors = colorService.colors().getResult();
            List<ProductImageResponse> productImages = productImageService.productImagesByProductId(product.getId()).getResult();

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
                    productImages
            );
            return new APICustomize<>(ApiError.OK.getCode(), ApiError.OK.getMessage(), productDetail);
        } else {
        	throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
    }
}