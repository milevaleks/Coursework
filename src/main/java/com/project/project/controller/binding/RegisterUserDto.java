package com.project.project.controller.binding;

import com.project.project.configuration.constraints.UniqueEmail;
import com.project.project.configuration.constraints.UniquePhone;
import com.project.project.configuration.constraints.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {

    @UniqueUsername
    @Size(min = 5, max = 15, message = "Username should be between 5 and 15 characters")
    private String username;

//    @Size(min = 6, max = 20, message = "Password should be between 6 and 20 characters")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[#?!@$%^&*-]).{6,20}$", message = "The password should have at least one Upper, one lower, " +
            "and at least one of the following @, -, _, ~, | and should be between 6 and 20 chars")
    private String password;

    @Email(message = "The email is required")
    @NotEmpty
    @UniqueEmail
    private String email;

    @UniquePhone
    @Pattern(regexp = "([0]|\\+359)[0-9]{2}[0-9]{7}", message = "Should match the bulgarian format for phone numbers. ")
    private String phone;
}
