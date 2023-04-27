package com.project.project.service.impl;

import com.project.project.configuration.ConstantsSettings;
import com.project.project.controller.binding.RegisterProductDto;
import com.project.project.domain.Category;
import com.project.project.domain.Image;
import com.project.project.domain.Product;
import com.project.project.repo.ProductRepository;
import com.project.project.service.ProductService;
import com.project.project.service.StorageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;
    private ModelMapper mapper;
    private StorageService storageService;


    @Autowired
    public ProductServiceImpl(ProductRepository repository, ModelMapper mapper, StorageService storageService) {
        this.repository = repository;
        this.mapper = mapper;
        this.storageService = storageService;
    }

    @Override
    public Page<Product> findProductByCode(String code, int page) {
        return repository.findByCode(code, getPage(page));
    }



    @Override
    public void saveProduct(RegisterProductDto dto) {
        Product entity = this.mapper.map(dto, Product.class);
        try {
            entity.setImages(createImages(dto.getImage(), entity));
        } catch (IOException e) {

        }
        repository.save(entity);
    }

    @Override
    public Page<Product> findAllProducts(int page) {
        return this.repository.findAll(getPage(page));
    }

    @Override
    public Product findProductById(String id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        this.repository.deleteById(id);
    }

    @Override
    public Page<Product> findAllByTitleStartingWithAndCategory(String title, Category category, int page) {

        return this.repository.findAllByTitleContainingIgnoreCaseAndCategory(title, category, getPage(page));
    }

    @Override
    public Page<Product> findAllByTitleStartingWith(String title, int page) {
        return this.repository.findAllByTitleContainingIgnoreCase(title, getPage(page));
    }

    @Override
    public Page<Product> findAllByCategory(Category category, int page) {
        return this.repository.findAllByCategory( category, getPage(page));
    }

    private Pageable getPage(int page){
        return PageRequest.of(page, ConstantsSettings.PAGE_SIZE);
    }

    private List<Image>createImages(List<MultipartFile> files, Product entity) throws IOException {
        List<Image> images = new ArrayList<>();
        for (MultipartFile file : files) {
            if(file.isEmpty()){
                continue;
            }
            storageService.store(file);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Image image = new Image(fileName);
            images.add(image);
            image.setProduct(entity);
        }
       return images;
    }
}
