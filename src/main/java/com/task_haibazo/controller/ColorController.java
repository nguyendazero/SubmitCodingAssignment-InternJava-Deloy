package com.task_haibazo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.ColorResponse;
import com.task_haibazo.service.ColorService;


@RestController
@RequestMapping("/api/v1/colors")
public class ColorController {

	@Autowired
	private ColorService colorService;
	
	@GetMapping("/")
	public ResponseEntity<APICustomize<List<ColorResponse>>> colors() {
	    APICustomize<List<ColorResponse>> response = colorService.colors();
	    HttpHeaders headers = new HttpHeaders();
	    if(response.getResult().isEmpty()) {
        	return new ResponseEntity<>(response, headers, HttpStatus.NOT_FOUND);
        }

	    return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}

	
}
