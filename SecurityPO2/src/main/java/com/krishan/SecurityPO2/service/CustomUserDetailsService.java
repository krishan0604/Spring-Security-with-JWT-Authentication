package com.krishan.SecurityPO2.service;

import com.krishan.SecurityPO2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.krishan.SecurityPO2.repo.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // ✅ Step 1: Find user from DB
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // ✅ Step 2: Convert user roles to authorities
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        // ✅ Step 3: Return UserDetails object
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,   // enabled
                true,   // account not expired
                true,   // credentials not expired
                true,   // account not locked
                user.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
