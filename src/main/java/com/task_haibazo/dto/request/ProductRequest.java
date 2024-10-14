package com.task_haibazo.dto.request;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.task_haibazo.validation.annotation.ValidProductName;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

    @ValidProductName(message = "Tên sản phẩm không được chứa số")
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @NotNull(message = "Tên sản phẩm không được để null")
    private String productName;

    @Lob
    @NotNull(message = "Mô tả không được để null")
    private String description;
    
    @Min(0)
    @Max(5)
    private double averageStars;
    
    @Min(0)
    private double price; // hoặc sử dụng BigDecimal

    @Min(0)
    private long totalView;
    
    @Future(message = "Ngày kết thúc bán phải trong tương lai")
    private Date saleEndDate;
    
    @Min(0)
    @Max(100)
    private int discount;
    
    @Lob
    @NotNull(message = "Ảnh sản phẩm không được để trống")
    private String image;
    
    @NotNull(message = "Thể loại sản phẩm không được để trống")
    private long categoryId;
}
