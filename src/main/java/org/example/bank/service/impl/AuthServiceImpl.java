package org.example.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.bank.dto.request.UserProfileRequest;
import org.example.bank.dto.response.LoginResponse;
import org.example.bank.dto.response.RegisterResponse;
import org.example.bank.entity.AppUser;
import org.example.bank.entity.User;
import org.example.bank.repository.UserRepository;
import org.example.bank.security.JwtUtil;
import org.example.bank.service.AuthService;
import org.example.bank.service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserProfileService userProfileService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public RegisterResponse registerCustomer(UserProfileRequest.AuthRequest<UserProfileRequest> request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        user = userRepository.save(user);

        UserProfileRequest requestData = request.getData().orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found")
        );

        requestData.setUser(user);

        userProfileService.createCustomer(requestData);

        return RegisterResponse.builder()
                .username(user.getUsername())
                .build();
    }

    @Override
    public LoginResponse login(UserProfileRequest.AuthRequest<String> request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .token(token)
                .build();
    }

}
