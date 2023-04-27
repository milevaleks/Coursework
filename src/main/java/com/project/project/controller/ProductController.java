package com.project.project.controller;

import com.project.project.controller.binding.EditProductDto;
import com.project.project.controller.binding.RegisterProductDto;
import com.project.project.domain.Category;
import com.project.project.domain.Image;
import com.project.project.domain.Product;
import com.project.project.service.ImageService;
import com.project.project.service.ProductService;
import com.project.project.service.StorageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    private StorageService storageService;
    private ImageService imageService;
    private ModelMapper mapper;

    @Autowired
    public ProductController(ProductService productService, StorageService storageService, ImageService imageService, ModelMapper mapper) {
        this.productService = productService;
        this.storageService = storageService;
        this.imageService = imageService;
        this.mapper = mapper;
    }

    @GetMapping
    public String searchProduct(@RequestParam(required = false, defaultValue = "") String title, @RequestParam(required = false, defaultValue = "ALL") Category category,
                                @RequestParam(required = false, defaultValue = "") String code, @RequestParam(required = false, defaultValue = "0") Integer pageNumber, Model model) {

        Page<Product> products = new PageImpl<Product>(new ArrayList<>());
        if (!code.equals("")) {
            Page<Product> productByCode = productService.findProductByCode(code, pageNumber);
            if (productByCode.getContent().size() != 0) {
                products = productByCode;
            }
        } else if (title.equals("") && category != Category.ALL) {
            products = productService.findAllByCategory(category, pageNumber);
        } else if (!title.equals("") && category == Category.ALL) {
            products = productService.findAllByTitleStartingWith(title, pageNumber);
        } else if (!title.equals("") && category != Category.ALL) {
            products = productService.findAllByTitleStartingWithAndCategory(title, category, pageNumber);
        } else {
            products = productService.findAllProducts(pageNumber);
        }
        model.addAttribute("products", products.getContent());
        model.addAttribute("paginationSize", products.getTotalPages());
        model.addAttribute("title", title);
        model.addAttribute("code", code);
        model.addAttribute("category", category);
        return "index-logged";
    }

    @GetMapping("/add")
    public String addProductGet(Model model) {
        if (!model.containsAttribute("registerProductDto")) {
            model.addAttribute("registerProductDto", new RegisterProductDto());
        }
        return "add-product";
    }

    @GetMapping("/edit")
    public String editProductGet(Model model) {
        if (!model.containsAttribute("editProductDto")) {
            model.addAttribute("editProductDto", new EditProductDto());
        }
        return "edit-product";
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute @Valid EditProductDto productDto, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-product";
        }
        this.productService.saveProduct(this.mapper.map(productDto, RegisterProductDto.class));
        return "redirect:/";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute @Valid RegisterProductDto productDto, BindingResult result) {
        if (result.hasErrors()) {
            return "add-product";
        }
        this.productService.saveProduct(productDto);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id, @RequestParam(required = false, defaultValue = "") String title, @RequestParam(required = false, defaultValue = "ALL") Category category,
                                @RequestParam(required = false, defaultValue = "") String code) {
        this.productService.deleteById(id);
        return String.format("redirect:/product?title=%s&code=%s&category=%s", title, code, category);

    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable String id, RedirectAttributes redirectAttributes) {
        Product productById = this.productService.findProductById(id);
        List<MultipartFile> files = new ArrayList<>();
        redirectAttributes.addFlashAttribute("editProductDto", new EditProductDto(productById.getId(),
                productById.getTitle(), productById.getDescription(), files,
                productById.getOriginalPrice(), productById.getSoldPrice(), productById.getQuantity(),
                productById.getCategory(), productById.getCode()
        ));
        return "redirect:/product/edit";
    }

    @GetMapping("/download/{id}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String id) {
        Image imageById = imageService.findImageById(id);
        Resource file = storageService.loadAsResource(imageById.getFileName());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
