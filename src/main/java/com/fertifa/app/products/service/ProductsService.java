package com.fertifa.app.products.service;

import com.fertifa.app.products.api.ProductsRequestDto;
import com.fertifa.app.products.api.ProductsResponseDto;

import java.util.List;

public interface ProductsService {
    ProductsResponseDto save(ProductsRequestDto productsRequestDto);

    List<ProductsResponseDto> findAll();

    ProductsResponseDto getOne(Long id);

    ProductsResponseDto updateProduct(ProductsRequestDto productsRequestDto);

    void deleteById(Long id);
}
