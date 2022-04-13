package com.alterra.relationship.controller;

import com.alterra.relationship.domain.dto.BrandDto;
import com.alterra.relationship.domain.dto.CategoryDto;
import com.alterra.relationship.service.BrandService;
import com.alterra.relationship.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "")
    public ResponseEntity<Object> createCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

}
