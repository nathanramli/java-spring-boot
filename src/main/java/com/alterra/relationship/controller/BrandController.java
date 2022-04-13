package com.alterra.relationship.controller;

import com.alterra.relationship.domain.dto.BrandDto;
import com.alterra.relationship.domain.dto.ProductDto;
import com.alterra.relationship.service.BrandService;
import com.alterra.relationship.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/brands")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @PostMapping(value = "")
    public ResponseEntity<Object> createBrand(@RequestBody BrandDto brandDto) {
        return brandService.createBrand(brandDto);
    }

}
