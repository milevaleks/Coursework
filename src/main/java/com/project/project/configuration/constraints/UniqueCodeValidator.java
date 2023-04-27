package com.project.project.configuration.constraints;

import com.project.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueCodeValidator implements ConstraintValidator<UniqueCode, String> {

    private ProductService service;

    @Autowired
    public UniqueCodeValidator(ProductService service) {
        this.service = service;
    }

    @Override
    public void initialize(UniqueCode constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return service.findProductByCode(value, 0).getContent().size() == 0;
    }
}
