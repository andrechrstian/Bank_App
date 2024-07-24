package org.example.bank.service.impl;

import lombok.AllArgsConstructor;
import org.example.bank.dto.request.UserProfileRequest;
import org.example.bank.dto.response.UserProfileResponse;
import org.example.bank.entity.UserProfile;
import org.example.bank.repository.UserProfileRepository;
import org.example.bank.service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;

    @Override
    public UserProfileResponse createCustomer(UserProfileRequest request) {

        UserProfile userProfile = userProfileRepository.save(
                UserProfile.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .dateOfBirth(request.getDateOfBirth())
                        .build()
        );

        return convertToCustomerResponse(userProfile);
    }

    @Override
    public UserProfileResponse getAllUserProfile() {
        return convertToCustomerResponse(userProfileRepository.findAllUserProfile());
    }

    @Override
    public UserProfileResponse updateUserProfile(UserProfileRequest request) {
        UserProfile existingUserProfile = findByIdOrThrowNotFound(request.getId());
        existingUserProfile.setFirstName(request.getFirstName());
        existingUserProfile.setLastName(request.getLastName());
        existingUserProfile.setDateOfBirth(request.getDateOfBirth());

        UserProfile updatedUserProfile = userProfileRepository.saveAndFlush(existingUserProfile);
        return convertToCustomerResponse(updatedUserProfile);
    }

    private UserProfile findByIdOrThrowNotFound(String id) {
        return userProfileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    private UserProfileResponse convertToCustomerResponse(UserProfile userProfile) {
        return UserProfileResponse.builder()
                .id(userProfile.getId())
                .firstName(userProfile.getFirstName())
                .lastName(userProfile.getLastName())
                .dateOfBirth(userProfile.getDateOfBirth())
                .build();
    }
}
