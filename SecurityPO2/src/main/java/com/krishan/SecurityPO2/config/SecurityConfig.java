package com.krishan.SecurityPO2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("securityfilterchain..3");

        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/user").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())  // login form enabled
                .logout(logout -> logout
                        .logoutUrl("/logout")  // 🔓 URL to hit for logout
                        .logoutSuccessUrl("/login?logout")  // 🔄 After logout redirect here
                        .invalidateHttpSession(true)  // 🧹 Invalidate session
                        .deleteCookies("JSESSIONID")   // 🍪 Clear session cookies
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
