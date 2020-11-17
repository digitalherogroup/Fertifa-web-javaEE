package com.fertifa.app.products.repository.products;

import com.fertifa.app.products.model.products.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products,Long> {
}
