package com.project.project.repo;

import com.project.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
        User findByUsernameIgnoreCase(String username);
        User findByPhone(String phone);
        User findByEmailIgnoreCase(String email);
}
