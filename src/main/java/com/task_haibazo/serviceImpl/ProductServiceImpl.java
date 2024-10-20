package com.task_haibazo.serviceImpl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.task_haibazo.dto.request.ProductRequest;
import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.CategoryResponse;
import com.task_haibazo.dto.response.ColorResponse;
import com.task_haibazo.dto.response.ProductDetailResponse;
import com.task_haibazo.dto.response.ProductImageResponse;
import com.task_haibazo.dto.response.ProductResponse;
import com.task_haibazo.dto.response.SizeResponse;
import com.task_haibazo.entity.Category;
import com.task_haibazo.entity.Product;
import com.task_haibazo.enums.ApiError;
import com.task_haibazo.exception.InvalidPageOrSizeException;
import com.task_haibazo.exception.ListProductEmptyException;
import com.task_haibazo.exception.ProductNotFoundException;
import com.task_haibazo.repository.CategoryRepository;
import com.task_haibazo.repository.ProductRepository;
import com.task_haibazo.service.CategoryService;
import com.task_haibazo.service.ColorService;
import com.task_haibazo.service.ProductImageService;
import com.task_haibazo.service.ProductService;
import com.task_haibazo.service.SizeService;
import com.task_haibazo.specification.ProductSpecification;
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
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public APICustomize<List<ProductResponse>> products(Long sizeId, Double minPrice, Double maxPrice, Long colorId,
            Long styleId, Long categoryId, String sortBy, String sortOrder, int page, int size) {

        if (page < 0 || size <= 0) {
            throw (page < 0) 
                ? new InvalidPageOrSizeException("Page không được nhỏ hơn 0") 
                : new InvalidPageOrSizeException("Size không được nhỏ hơn hoặc bằng 0");
        }

        // Tạo specification với các tiêu chí tìm kiếm
        Specification<Product> spec = Specification.where(ProductSpecification.hasSize(sizeId))
                .and(ProductSpecification.hasColor(colorId))
                .and(ProductSpecification.hasMinPrice(minPrice))
                .and(ProductSpecification.hasMaxPrice(maxPrice))
                .and(ProductSpecification.hasStyle(styleId))
                .and(ProductSpecification.hasCategory(categoryId))
                .and(ProductSpecification.sortBy(sortBy, sortOrder));

        // Sử dụng Pageable từ Spring Data
        Pageable pageable = PageRequest.of(page, size);

        // Tìm danh sách sản phẩm với Specification và phân trang
        List<Product> products = productRepository.findAll(spec, pageable).getContent();

        if (products.isEmpty()) {
            throw new ListProductEmptyException("Danh sách rỗng, không tìm thấy sản phẩm nào");
        }

        List<ProductResponse> productResponses = products.stream()
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
    
    @Override
    public APICustomize<ProductResponse> save(ProductRequest productRequest) {

        Product product = new Product();
        APICustomize<CategoryResponse> categoryResponse = categoryService.category(productRequest.getCategoryId());
        Category category = categoryRepository.findById(categoryResponse.getResult().getId())
                .orElseThrow(() -> new RuntimeException("Category không tồn tại"));
        
        product.setProductName(productRequest.getProductName());
        product.setDescription(productRequest.getDescription());
        product.setAverageStars(productRequest.getAverageStars());
        product.setPrice(productRequest.getPrice());
        product.setTotalView(productRequest.getTotalView());
        product.setSaleEndDate(productRequest.getSaleEndDate());
        product.setDiscount(productRequest.getDiscount());
        product.setImage(productRequest.getImage());
        product.setCategory(category);
        
     // Lưu Product vào cơ sở dữ liệu
        Product savedProduct = productRepository.save(product);

        // Tạo phản hồi trả về cho người dùng
        ProductResponse productResponse = new ProductResponse(
        		savedProduct.getId(),
        		savedProduct.getProductName(),
        		savedProduct.getPrice(),
        		savedProduct.getDiscount(),
        		savedProduct.getImage(),
        		savedProduct.getAverageStars()	
        );
    	
        return new APICustomize<>(ApiError.CREATED.getCode(), ApiError.CREATED.getMessage(), productResponse);
    }
    
}