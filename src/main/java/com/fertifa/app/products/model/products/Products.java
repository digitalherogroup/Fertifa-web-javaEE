package com.fertifa.app.products.model.products;

import com.fertifa.app.products.model.categories.Categories;
import com.fertifa.app.products.model.paymenttype.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable=false)
    private Long id;
    @CreationTimestamp
    @Column(name = "created")
    private Date created;

    @UpdateTimestamp
    @Column(name = "updated")
    private Date updated;

    private String imageLink;
    private String title;
    @Column(columnDefinition = "varchar(255)")
    private String description;
    @Column(columnDefinition = "LONGTEXT")
    private String shortDescription;
    @Column(columnDefinition = "double default 0.0")
    private Double price;
    @Column(columnDefinition = "varchar default 0")
    private String published;
    @ManyToMany
    private List<Categories> categories;
    @Column(columnDefinition = "varchar default fixed")
    @ManyToMany
    private List<PaymentType> paymentType;

}
