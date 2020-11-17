package com.fertifa.app.products.impl;

import com.fertifa.app.products.api.ProductsRequestDto;
import com.fertifa.app.products.api.ProductsResponseDto;
import com.fertifa.app.products.exception.IdNotFoundException;
import com.fertifa.app.products.exception.ObjectNullException;
import com.fertifa.app.products.mapper.ProductsMapper;
import com.fertifa.app.products.model.products.Products;
import com.fertifa.app.products.repository.products.ProductsRepository;
import com.fertifa.app.products.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductsService {
    private final ProductsMapper productsMapper;
    private final ProductsRepository productsRepository;

    @Override
    public ProductsResponseDto save(ProductsRequestDto productsRequestDto) {
        Products products = productsMapper.fromProductsRequestDtoToProducts(productsRequestDto);
        return productsMapper.fromSaveProductToProductResponseDto(productsRepository.save(products));
    }

    @Override
    public List<ProductsResponseDto> findAll() {
        return productsMapper.fromNonToProductsResponseDeto(productsRepository.findAll());
    }

    @Override
    public ProductsResponseDto getOne(Long id) {
        return productsMapper.fromSaveProductToProductResponseDto(productsRepository.getOne(id));
    }

    @Override
    public ProductsResponseDto updateProduct(ProductsRequestDto productsRequestDto) {
        if(null == productsRequestDto) throw new ObjectNullException();
        if(null == productsRequestDto.getId()) throw new IdNotFoundException(productsRequestDto.getId());
        Products products = productsMapper.fromProductsRequestDtoToProducts(productsRequestDto);
        products.setId(productsRequestDto.getId());
        products.setUpdated(new Timestamp(new Date().getTime()));
        return productsMapper.fromSaveProductToProductResponseDto(productsRepository.save(products));
    }

    @Override
    public void deleteById(Long id) {
        if(!productsRepository.existsById(id))throw new IdNotFoundException(id);
        productsRepository.deleteById(id);
    }

}
