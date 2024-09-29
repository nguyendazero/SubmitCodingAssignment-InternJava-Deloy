package com.task_haibazo.service;

import java.util.List;

import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.SizeResponse;

public interface SizeService {

	public APICustomize<List<SizeResponse>> sizes();

}
