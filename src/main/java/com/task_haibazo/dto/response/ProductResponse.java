package com.task_haibazo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponse {
    private long id;
    private String productName;
    private double price;
    private double discount;
    private String image;
    private double averageStars;
}