package com.project.project.configuration.constraints;

import com.project.project.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniquePhoneValidator implements ConstraintValidator<UniquePhone, String> {

    private UserRepository repository;

    @Autowired
    public UniquePhoneValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(UniquePhone constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return repository.findByPhone(value) == null;
    }
}
