package com.fertifa.app.products.repository.categories;

import com.fertifa.app.products.model.categories.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories,Long> {
}
