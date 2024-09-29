package com.task_haibazo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.SizeResponse;
import com.task_haibazo.service.SizeService;

@RestController
@RequestMapping("/api/v1/sizes")
public class SizeController {

	@Autowired
	private SizeService sizeService;
	
	@GetMapping("/")
	public ResponseEntity<APICustomize<List<SizeResponse>>> sizes() {
	    APICustomize<List<SizeResponse>> response = sizeService.sizes();
	    HttpHeaders headers = new HttpHeaders();
	    if(response.getResult().isEmpty()) {
        	return new ResponseEntity<>(response, headers, HttpStatus.NOT_FOUND);
        }

	    return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}

	
}
