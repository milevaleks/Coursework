package com.project.project.configuration.constraints;

import com.project.project.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private UserRepository repository;

    @Autowired
    public UniqueUsernameValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return repository.findByUsernameIgnoreCase(value) == null;
    }
}
