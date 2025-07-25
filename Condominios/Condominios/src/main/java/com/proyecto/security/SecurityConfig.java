package com.proyecto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.proyecto.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	   @Autowired
	    private CustomUserDetailsService userDetailsService;
	   @Autowired
	   private CustomLoginSuccessHandler loginSuccessHandler;
	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/login", "/css/**").permitAll()
	                .requestMatchers("/images/**").permitAll()
	                .requestMatchers("/usuario/registro", "/registro").permitAll()  // ← AÑADIR ESTA LÍNEA
	                .requestMatchers("/admin/**").hasRole("ADMINISTRADOR")
	                .requestMatchers("/propietario/**").hasRole("PROPIETARIO")
	                .anyRequest().authenticated()
	            )
	            .formLogin(form -> form
	                .loginPage("/login")
	                .successHandler(loginSuccessHandler)
	                .permitAll()
	            )
	            .logout(logout -> logout.permitAll());

	        return http.build();
	    }

	    @Bean
	    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
	        return http.getSharedObject(AuthenticationManagerBuilder.class)
	            .userDetailsService(userDetailsService::loadUserByUsername)
	            .passwordEncoder(passwordEncoder())
	            .and()
	            .build();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}
