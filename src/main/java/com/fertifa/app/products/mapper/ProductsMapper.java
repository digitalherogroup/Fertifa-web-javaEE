package com.fertifa.app.products.mapper;

import com.fertifa.app.products.api.ProductsRequestDto;
import com.fertifa.app.products.api.ProductsResponseDto;
import com.fertifa.app.products.model.products.Products;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductsMapper {
    public Products fromProductsRequestDtoToProducts(ProductsRequestDto productsRequestDto) {
        Products products = new Products();
        products.setTitle(productsRequestDto.getTitle());
        products.setDescription(productsRequestDto.getDescription());
        products.setShortDescription(productsRequestDto.getShortDescription());
        products.setPrice(productsRequestDto.getPrice());
        products.setPublished(productsRequestDto.getPublished());
        products.setCategories(Collections.singletonList(productsRequestDto.getCategories()));
        //products.setPaymentType(Collections.singletonList(productsRequestDto.getPaymentType()));
        products.setImageLink(productsRequestDto.getImageLink());
        return products;
    }

    public ProductsResponseDto fromSaveProductToProductResponseDto(Products products) {
        return ProductsResponseDto
                .builder()
                .id(products.getId())
                .created(products.getCreated())
                .updated(products.getUpdated())
                .title(products.getTitle())
                .shortDescription(products.getShortDescription())
                .description(products.getDescription())
                .price(products.getPrice())
                .published(products.getPublished())
                .categories(products.getCategories())
                .paymentType(products.getPaymentType())
                .imageLink(products.getImageLink())
                .build();

    }
    public List<ProductsResponseDto> fromNonToProductsResponseDeto(List<Products> productsList) {
        return productsList.stream()
                .map(products -> new ProductsResponseDto(
                        products.getId(),
                        products.getCreated(),
                        products.getUpdated(),
                        products.getImageLink(),
                        products.getTitle(),
                        products.getShortDescription(),
                        products.getDescription(),
                        products.getPrice(),
                        products.getPublished(),
                        products.getCategories(),
                        products.getPaymentType()
                ))
                .collect(Collectors.toList());
    }

    public Products fromProductsRequestDtoToProductsById(Products products) {
        products.setTitle(products.getTitle());
        products.setImageLink(products.getImageLink());
        products.setDescription(products.getDescription());
        products.setShortDescription(products.getShortDescription());
        products.setPrice(products.getPrice());
        products.setPublished(products.getPublished());
        products.setCategories(products.getCategories());
        products.setPaymentType(products.getPaymentType());
        return products;
    }
}
