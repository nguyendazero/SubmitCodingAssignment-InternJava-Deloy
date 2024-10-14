package com.task_haibazo.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {

	@NotBlank(message = "categoryName khong duoc de trong")
	private String categoryName;
	
}
