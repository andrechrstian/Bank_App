package org.example.bank.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    public UserDetails loadUserById(String id);
}
