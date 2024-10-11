package com.task_haibazo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.SizeResponse;
import com.task_haibazo.entity.Size;
import com.task_haibazo.enums.ApiError;
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

	    if (sizes.isEmpty()) {
	        return new APICustomize<>(ApiError.NOT_FOUND.getCode(), ApiError.NOT_FOUND.getMessage(), sizeResponseList);
	    }

	    return new APICustomize<>(ApiError.OK.getCode(), ApiError.OK.getMessage(), sizeResponseList);
	}

}
