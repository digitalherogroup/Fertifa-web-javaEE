package com.fertifa.app.products.api;

import com.fertifa.app.products.model.categories.Categories;
import com.fertifa.app.products.model.paymenttype.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductsRequestDto {
    private Long id;
    private String imageLink;
    private String title;
    private String description;
    private String shortDescription;
    private Double price;
    private String published;
    private Categories categories;
    private PaymentType paymentType;
}
