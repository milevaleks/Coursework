package com.project.project.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;


@NoArgsConstructor
@Table
@Entity
@Data
public class Image extends BaseEntity {

    @NonNull
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public Image(@NonNull String fileName) {
        this.fileName = fileName;

    }
}
