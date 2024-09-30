package com.task_haibazo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.task_haibazo.dto.response.APICustomize;
import com.task_haibazo.dto.response.StyleResponse;
import com.task_haibazo.entity.Style;
import com.task_haibazo.repository.StyleRepository;
import com.task_haibazo.service.StyleService;

@Service
public class StyleServiceImpl implements StyleService{

	@Autowired
    private StyleRepository styleRepository;

    @Override
    public APICustomize<List<StyleResponse>> styles() {
        List<Style> styles = styleRepository.findAll();
        List<StyleResponse> styleResponseList = styles.stream()
                .map(style -> new StyleResponse(style.getId(), style.getStyleName()))
                .collect(Collectors.toList());

        String message = styles.isEmpty() ? "No styles found!" : "All styles retrieved successfully!";
        int statusCode = styles.isEmpty() ? HttpStatus.NOT_FOUND.value() : HttpStatus.OK.value();
        
        // Tạo API chuẩn với statuCode, message, result
        return new APICustomize<>(statusCode, message, styleResponseList);
    }
   

}
