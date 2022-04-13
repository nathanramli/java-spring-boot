package com.alterra.relationship.service;

import com.alterra.relationship.constant.ResponseMessage;
import com.alterra.relationship.domain.dao.BrandDao;
import com.alterra.relationship.domain.dao.CategoryDao;
import com.alterra.relationship.domain.dto.BrandDto;
import com.alterra.relationship.domain.dto.CategoryDto;
import com.alterra.relationship.payload.Response;
import com.alterra.relationship.repository.BrandRepository;
import com.alterra.relationship.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<Object> createCategory(CategoryDto category) {
        CategoryDao categoryDao = CategoryDao
                .builder()
                .name(category.getName())
                .build();

        categoryRepository.save(categoryDao);

        return Response.build(ResponseMessage.SUCCESS, HttpStatus.CREATED, CategoryDto.builder()
                .id(categoryDao.getId())
                .name(categoryDao.getName())
                .build());
    }
}
