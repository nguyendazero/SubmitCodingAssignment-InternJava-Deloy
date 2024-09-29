package com.task_haibazo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.service.StyleService;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import com.task_haibazo.dto.response.StyleResponse;

@RestController
@RequestMapping("/api/v1/styles")
public class StyleController {
	@Autowired
    private StyleService styleService;

    @GetMapping("/")
    public ResponseEntity<APICustomize<List<StyleResponse>>> styles() {
        APICustomize<List<StyleResponse>> response = styleService.styles();
        HttpHeaders headers = new HttpHeaders();
        if(response.getResult().isEmpty()) {
        	return new ResponseEntity<>(response, headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
