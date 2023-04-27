package com.project.project.controller;

import com.project.project.controller.binding.RegisterUserDto;
import com.project.project.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(("/register"))
public class AuthController {

    private AuthenticationService service;

    public AuthController(AuthenticationService service) {
        this.service = service;
    }

    @GetMapping
    public String register(Model model){
        if(!model.containsAttribute("registerDto")){
            model.addAttribute("registerDto", new RegisterUserDto());
        }
        return "register-user";
    }

    @PostMapping
    public String postRegister(@ModelAttribute @Valid RegisterUserDto dto, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("registerDto", dto);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDto", result);
        }else{
            service.register(dto);
            return "redirect:/login";
        }
        return "redirect:/register";
    }
}
