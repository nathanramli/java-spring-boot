package com.alterra.relationship.domain.dto.Response;

import com.alterra.relationship.domain.dto.ProductDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaginationProductsResponse {
    private List<ProductDto> products;
    private int size;
    private int page;
    private int totalPage;
}