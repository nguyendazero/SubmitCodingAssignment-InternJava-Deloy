package com.task_haibazo.dto.request;

import java.util.Date;

import com.task_haibazo.validation.annotation.NotAm;
import com.task_haibazo.validation.annotation.ValidProductName;
import com.task_haibazo.validation.annotation.ValidRange;

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
    @NotBlank(message = "mô tả sản phảm không được để trống")
    private String description;

    @ValidRange(min = 0, max = 5, message = "Số sao trung bình phải từ 0 đến 5")
    private Double averageStars;  

    @NotAm(message = "giá tiền không được nhỏ hơn 0")
    private Double price;

    private Long totalView;

    @Future(message = "Ngày kết thúc bán phải trong tương lai!")
    private Date saleEndDate;

    @ValidRange(min = 0, max = 100, message = "Giảm giá phải từ 0 đến 100")
    private Double discount;

    @Lob
    @NotNull(message = "Ảnh sản phẩm không được để trống")
    private String image;

    @NotNull(message = "Thể loại sản phẩm không được để trống")
    private Long categoryId;
}
