package org.example.bank.service;

import org.example.bank.dto.request.UserProfileRequest;
import org.example.bank.dto.response.UserProfileResponse;

import java.util.List;

public interface UserProfileService {
    UserProfileResponse createCustomer (UserProfileRequest request);
    UserProfileResponse getAllUserProfile ();
    UserProfileResponse updateUserProfile (UserProfileRequest request);
}
