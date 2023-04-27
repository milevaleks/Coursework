package com.project.project.controller.binding;

import com.project.project.configuration.constraints.UniqueCode;
import com.project.project.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterProductDto {


    private  String id;

    @NotEmpty
    @Size(min = 1, max = 50)
    private String title;

    @Size(max = 2000)
    private String description;

    @Size(max = 3)
    private List<MultipartFile> image;

    @NotNull(message = "Required")
    @PositiveOrZero
    private BigDecimal originalPrice;

    @PositiveOrZero
    @NotNull(message = "Required")
    private BigDecimal soldPrice;

    @PositiveOrZero
    private int quantity;


    private Category category;

    @UniqueCode
    @NotEmpty
    private String code;
}
