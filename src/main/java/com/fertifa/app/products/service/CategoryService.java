package com.fertifa.app.products.service;

import com.fertifa.app.products.model.categories.Categories;

import java.util.List;

public interface CategoryService {
    List<Categories> findAll();
}
