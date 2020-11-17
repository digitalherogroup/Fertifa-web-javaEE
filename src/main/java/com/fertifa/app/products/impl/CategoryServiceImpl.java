package com.fertifa.app.products.impl;

import com.fertifa.app.products.model.categories.Categories;
import com.fertifa.app.products.repository.categories.CategoriesRepository;
import com.fertifa.app.products.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoriesRepository categoriesRepository;
    @Override
    public List<Categories> findAll() {
        return categoriesRepository.findAll();
    }
}
