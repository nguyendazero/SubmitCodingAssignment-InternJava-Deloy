package com.task_haibazo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.ColorResponse;
import com.task_haibazo.entity.Color;
import com.task_haibazo.enums.ApiError;
import com.task_haibazo.repository.ColorRepository;
import com.task_haibazo.service.ColorService;

@Service
public class ColorServiceImpl implements ColorService{
	
	@Autowired
	private ColorRepository colorRepository;

	@Override
	public APICustomize<List<ColorResponse>> colors() {
	    List<Color> colors = colorRepository.findAll();

	    List<ColorResponse> colorResponseList = colors.stream()
	            .map(color -> new ColorResponse(color.getId(), color.getColorName()))
	            .collect(Collectors.toList());

	    if (colors.isEmpty()) {
	        return new APICustomize<>(ApiError.NOT_FOUND.getCode(), ApiError.NOT_FOUND.getMessage(), colorResponseList);
	    }

	    return new APICustomize<>(ApiError.OK.getCode(), ApiError.OK.getMessage(), colorResponseList);
	}

}
