package com.alterra.relationship.service;

import com.alterra.relationship.constant.ResponseMessage;
import com.alterra.relationship.domain.dao.BrandDao;
import com.alterra.relationship.domain.dao.CategoryDao;
import com.alterra.relationship.domain.dao.ProductDao;
import com.alterra.relationship.domain.dto.BrandDto;
import com.alterra.relationship.domain.dto.CategoryDto;
import com.alterra.relationship.domain.dto.ProductDto;
import com.alterra.relationship.domain.dto.Request.PaginationRequest;
import com.alterra.relationship.domain.dto.Response.PaginationProductsResponse;
import com.alterra.relationship.payload.Response;
import com.alterra.relationship.repository.BrandRepository;
import com.alterra.relationship.repository.CategoryRepository;
import com.alterra.relationship.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<Object> getProductById(Long id) {
        Optional<ProductDao> productDaoOptional = productRepository.findById(id);

        if (productDaoOptional.isEmpty())
            return Response.build(ResponseMessage.NOT_FOUND, HttpStatus.BAD_REQUEST, null);

        ProductDao productDao = productDaoOptional.get();
        return Response.build(ResponseMessage.SUCCESS, HttpStatus.OK, ProductDto.builder()
                .id(productDao.getId())
                .price(productDao.getPrice())
                .name(productDao.getName())
                .build());
    }

    public ResponseEntity<Object> getAllProduct() {
        List<ProductDao> productDaoList = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for(ProductDao product : productDaoList) {
            productDtoList.add(
                    ProductDto.builder()
                            .id(product.getId())
                            .price(product.getPrice())
                            .name(product.getName())
                            .modelYear(product.getModelYear())
                            .brand(
                                    BrandDto
                                            .builder()
                                            .id(product.getBrand().getId())
                                            .name(product.getBrand().getName())
                                            .build()
                            )
                            .category(
                                    CategoryDto
                                            .builder()
                                            .id(product.getCategory().getId())
                                            .name(product.getCategory().getName())
                                            .build()
                            )
                            .build()
            );
        }
        return Response.build(ResponseMessage.SUCCESS, HttpStatus.OK, productDtoList);
    }

    public ResponseEntity<Object> getAllProduct(PaginationRequest paginationRequest) {
        Pageable pageable = PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize());
        Page<ProductDao> productDaoList = productRepository.findAll(pageable);
        List<ProductDto> productDtoList = new ArrayList<>();
        for(ProductDao product : productDaoList.getContent()) {
            productDtoList.add(
                    ProductDto.builder()
                            .id(product.getId())
                            .price(product.getPrice())
                            .name(product.getName())
                            .modelYear(product.getModelYear())
                            .brand(
                                    BrandDto
                                            .builder()
                                            .id(product.getBrand().getId())
                                            .name(product.getBrand().getName())
                                            .build()
                            )
                            .category(
                                    CategoryDto
                                            .builder()
                                            .id(product.getCategory().getId())
                                            .name(product.getCategory().getName())
                                            .build()
                            )
                            .build()
            );
        }
        return Response.build(ResponseMessage.SUCCESS, HttpStatus.OK,
                PaginationProductsResponse
                        .builder()
                        .page(productDaoList.getNumber())
                        .products(productDtoList)
                        .totalPage(productDaoList.getTotalPages())
                        .size(productDaoList.getSize())
                        .build());
    }

    public ResponseEntity<Object> getAllProductOrderByCategory(boolean asc) {
        List<ProductDao> productDaoList = new ArrayList<>();
        if (asc)
            productDaoList = productRepository.findAllByOrderByCategoryAsc();
        else
            productDaoList = productRepository.findAllByOrderByCategoryDesc();

        List<ProductDto> productDtoList = new ArrayList<>();
        for(ProductDao product : productDaoList) {
            productDtoList.add(
                    ProductDto.builder()
                            .id(product.getId())
                            .price(product.getPrice())
                            .name(product.getName())
                            .modelYear(product.getModelYear())
                            .brand(
                                    BrandDto
                                            .builder()
                                            .id(product.getBrand().getId())
                                            .name(product.getBrand().getName())
                                            .build()
                            )
                            .category(
                                    CategoryDto
                                            .builder()
                                            .id(product.getCategory().getId())
                                            .name(product.getCategory().getName())
                                            .build()
                            )
                            .build()
            );
        }
        return Response.build(ResponseMessage.SUCCESS, HttpStatus.OK, productDtoList);
    }

    public ResponseEntity<Object> searchAllProducts(String q) {
        List<ProductDao> productDaoList = productRepository.findAllByNameContaining(q);
        List<ProductDto> productDtoList = new ArrayList<>();
        for(ProductDao product : productDaoList) {
            productDtoList.add(
                    ProductDto.builder()
                            .id(product.getId())
                            .price(product.getPrice())
                            .name(product.getName())
                            .modelYear(product.getModelYear())
                            .brand(
                                    BrandDto
                                            .builder()
                                            .id(product.getBrand().getId())
                                            .name(product.getBrand().getName())
                                            .build()
                            )
                            .category(
                                    CategoryDto
                                            .builder()
                                            .id(product.getCategory().getId())
                                            .name(product.getCategory().getName())
                                            .build()
                            )
                            .build()
            );
        }
        return Response.build(ResponseMessage.SUCCESS, HttpStatus.OK, productDtoList);
    }

    public ResponseEntity<Object> createProduct(ProductDto product) {
        Optional<BrandDao> brandDao = brandRepository.findById(product.getBrand().getId());
        if (brandDao.isEmpty())
            return Response.build(ResponseMessage.NOT_FOUND, HttpStatus.BAD_REQUEST, null);

        Optional<CategoryDao> categoryDao = categoryRepository.findById(product.getCategory().getId());
        if (categoryDao.isEmpty())
            return Response.build(ResponseMessage.NOT_FOUND, HttpStatus.BAD_REQUEST, null);

        ProductDao productDao = ProductDao
                .builder()
                .name(product.getName())
                .modelYear(product.getModelYear())
                .price(product.getPrice())
                .brand(brandDao.get())
                .category(categoryDao.get())
                .build();

        productRepository.save(productDao);
        return Response.build(ResponseMessage.SUCCESS, HttpStatus.CREATED, ProductDto.builder()
                .id(productDao.getId())
                .name(product.getName())
                .modelYear(product.getModelYear())
                .price(product.getPrice())
                .brand(
                        BrandDto
                                .builder()
                                .id(brandDao.get().getId())
                                .name(brandDao.get().getName())
                                .build()
                )
                .category(
                        CategoryDto
                                .builder()
                                .id(categoryDao.get().getId())
                                .name(categoryDao.get().getName())
                                .build()
                )
                .build());
    }

    public ResponseEntity<Object> updateProduct(Long id, ProductDto product) {
        Optional<ProductDao> productDaoOptional = productRepository.findById(id);

        if (productDaoOptional.isEmpty())
            return Response.build(ResponseMessage.NOT_FOUND, HttpStatus.BAD_REQUEST, null);

        ProductDao productDao = productDaoOptional.get();
        productDao.setPrice(product.getPrice());
        productDao.setName(product.getName());
        productRepository.save(productDao);

        return Response.build(ResponseMessage.SUCCESS, HttpStatus.OK, ProductDto.builder()
                .id(productDao.getId())
                .price(productDao.getPrice())
                .name(productDao.getName())
                .build());
    }

    public ResponseEntity<Object> deleteProduct(Long id) {
        Optional<ProductDao> productDaoOptional = productRepository.findById(id);

        if (productDaoOptional.isEmpty())
            return Response.build(ResponseMessage.NOT_FOUND, HttpStatus.BAD_REQUEST, null);

        productRepository.deleteById(id);
        return Response.build(ResponseMessage.SUCCESS, HttpStatus.OK, null);
    }

}
