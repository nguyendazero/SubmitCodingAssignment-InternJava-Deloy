package com.task_haibazo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ProductDetailResponse {
    private long id;
    private String description;
    private long totalView;
    private String productName;
    private double price;
    private double discount;
    private String image;
    private Date saleEndDate; 
    private double averageStars;
    private List<SizeResponse> sizes;  
    private List<ColorResponse> colors; 
    private List<ProductImageResponse> productImages;
}
