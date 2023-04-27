package com.project.project.configuration.init;

import com.project.project.domain.User;
import com.project.project.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInit implements CommandLineRunner {
    private UserRepository repository;
    private PasswordEncoder encoder;

    public UserInit(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            User user = new User("test", encoder.encode("1234"), "test@abv.bg", "0877160368");
            repository.save(user);
        }
    }
}