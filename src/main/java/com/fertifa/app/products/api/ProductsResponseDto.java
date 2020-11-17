package com.fertifa.app.products.api;

import com.fertifa.app.products.model.categories.Categories;
import com.fertifa.app.products.model.paymenttype.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductsResponseDto {
    private Long id;
    private Date created;
    private Date updated;
    private String imageLink;
    private String title;
    private String description;
    private String shortDescription;
    private Double price;
    private String published;
    private List<Categories> categories;
    private List<PaymentType> paymentType;
}
