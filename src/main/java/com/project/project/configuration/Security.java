package com.project.project.configuration;

import com.project.project.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class Security {

    @Autowired
    private AuthenticationService authenticationService;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security.httpBasic().disable();
        security.headers().frameOptions().disable();
        security.csrf().ignoringAntMatchers("/h2-console/**");
        security.authorizeRequests((request) -> request.antMatchers("/h2-console/**").permitAll()
                        .antMatchers("/product/**").authenticated().antMatchers("/register", "/login")
                        .anonymous())
                .formLogin((form) -> form.loginPage("/login").loginProcessingUrl("/login"))
                .logout((logout)->logout.logoutUrl("/logout"))
                .userDetailsService((UserDetailsService) authenticationService);
        return security.build();
    }

}
