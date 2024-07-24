package org.example.bank.service;

import org.example.bank.dto.request.UserProfileRequest;
import org.example.bank.dto.response.LoginResponse;
import org.example.bank.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerCustomer (UserProfileRequest.AuthRequest<UserProfileRequest> authRequest);
    LoginResponse login (UserProfileRequest.AuthRequest<String> request);
}
