package com.project.project.controller;

import com.project.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {

    private ProductService productService;

    @Autowired
    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String front(Principal principal, Model model) {
        if(principal !=null) {
            return authenticated();
        }
        return anonymous();
    }


    private String authenticated() {
        return "redirect:/product";
    }

    private String anonymous() {
        return "redirect:/login";
    }
}
