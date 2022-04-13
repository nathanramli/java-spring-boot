package com.alterra.relationship.controller;

import com.alterra.relationship.domain.dto.ProductDto;
import com.alterra.relationship.domain.dto.Request.PaginationRequest;
import com.alterra.relationship.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "")
    public ResponseEntity<Object> getAllProducts() {
        return productService.getAllProduct();
    }

    @GetMapping(value = "/sort-by-category")
    public ResponseEntity<Object> getProductsSortByCategory(@RequestParam String sort) {
        return productService.getAllProductOrderByCategory(!sort.equalsIgnoreCase("DESC"));
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Object> getProductsSearch(@RequestParam String product_name) {
        return productService.searchAllProducts(product_name);
    }

    @PostMapping(value = "/pagination")
    public ResponseEntity<Object> getPagination(@RequestBody PaginationRequest paginationRequest) {
        return productService.getAllProduct(paginationRequest);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }
}
