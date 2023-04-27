package com.project.project.service.impl;

import com.project.project.controller.binding.RegisterUserDto;
import com.project.project.domain.User;
import com.project.project.repo.UserRepository;
import com.project.project.service.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService, UserDetailsService {

    private UserRepository repository;
    private ModelMapper mapper;
    private PasswordEncoder encoder;

    @Autowired
    public AuthenticationServiceImpl(UserRepository repository, ModelMapper mapper, PasswordEncoder encoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    public void register(RegisterUserDto registerDto) {
        User mappedUser = this.mapper.map(registerDto, User.class);
        mappedUser.setPassword(encoder.encode(mappedUser.getPassword()));
        repository.save(mappedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.repository.findByUsernameIgnoreCase(username);
    }
}
