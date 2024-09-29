package com.task_haibazo.service;

import java.util.List;

import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.ColorResponse;

public interface ColorService {

	public APICustomize<List<ColorResponse>> colors() ;

}
