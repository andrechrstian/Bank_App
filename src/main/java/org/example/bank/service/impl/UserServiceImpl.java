package org.example.bank.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.bank.entity.AppUser;
import org.example.bank.entity.User;
import org.example.bank.repository.UserRepository;
import org.example.bank.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;

    @Override
    @PreAuthorize("hasRole('CUSTOMER')")
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(email)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid email"));

        return AppUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    @Override
    public UserDetails loadUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid id -" + id));

        return AppUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
