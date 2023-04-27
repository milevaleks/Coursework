package com.project.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "cuser")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails {

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String phone;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority auth = () -> "USER";
        return List.of(auth) ;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

