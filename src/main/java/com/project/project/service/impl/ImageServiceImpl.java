package com.project.project.service.impl;

import com.project.project.domain.Image;
import com.project.project.repo.ImageRepository;
import com.project.project.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    private ImageRepository repository;

    @Autowired
    public ImageServiceImpl(ImageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Image findImageById(String id) {
        return repository.findById(id).orElse(null);
    }
}
