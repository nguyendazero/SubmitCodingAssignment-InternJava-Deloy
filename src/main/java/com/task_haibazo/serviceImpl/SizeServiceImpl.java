package com.task_haibazo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.SizeResponse;
import com.task_haibazo.entity.Size;
import com.task_haibazo.repository.SizeRepository;
import com.task_haibazo.service.SizeService;

@Service
public class SizeServiceImpl implements SizeService{

	@Autowired
	private SizeRepository sizeRepository;
	
	@Override
	public APICustomize<List<SizeResponse>> sizes() {
	    List<Size> sizes = sizeRepository.findAll(); 
	    List<SizeResponse> sizeResponseList = sizes.stream()
	            .map(size -> new SizeResponse(size.getId(), size.getSizeName()))
	            .collect(Collectors.toList());

	    String message = sizes.isEmpty() ? "No sizes found!" : "All sizes retrieved successfully!";
	    int statusCode = sizes.isEmpty() ? HttpStatus.NOT_FOUND.value() : HttpStatus.OK.value();
	    
	    // Tạo API chuẩn với statuCode, message, result
	    return new APICustomize<>(statusCode, message, sizeResponseList);
	}
}
