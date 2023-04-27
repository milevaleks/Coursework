package com.project.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    private String title;
    private String description;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> images;
    private BigDecimal originalPrice;
    private BigDecimal soldPrice;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(unique = true)
    private String code;
}
