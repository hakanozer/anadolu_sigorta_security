package com.works.configs;

import com.works.services.CustomerDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final CustomerDetailService detailService;
    final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers("/api/v1/product/**").hasRole("product")
        .antMatchers("/api/v1/note/**").hasRole("note")
        .and().csrf().disable().formLogin().disable();
        http.headers().frameOptions().disable();
    }
}

/*
* erkan@mail.com -> product
* serkan@mail.com -> note
* zehra@mail.com -> product, note
* */
