package com.alterra.relationship.service;

import com.alterra.relationship.constant.ResponseMessage;
import com.alterra.relationship.domain.dao.BrandDao;
import com.alterra.relationship.domain.dao.CategoryDao;
import com.alterra.relationship.domain.dao.ProductDao;
import com.alterra.relationship.domain.dto.BrandDto;
import com.alterra.relationship.domain.dto.CategoryDto;
import com.alterra.relationship.domain.dto.ProductDto;
import com.alterra.relationship.payload.Response;
import com.alterra.relationship.repository.BrandRepository;
import com.alterra.relationship.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public ResponseEntity<Object> createBrand(BrandDto brand) {
        BrandDao brandDao = BrandDao
                .builder()
                .name(brand.getName())
                .build();

        brandRepository.save(brandDao);

        return Response.build(ResponseMessage.SUCCESS, HttpStatus.CREATED, BrandDto.builder()
                .id(brandDao.getId())
                .name(brandDao.getName())
                .build());
    }
}
