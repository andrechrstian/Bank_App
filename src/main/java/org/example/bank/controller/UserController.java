package org.example.bank.controller;

import lombok.RequiredArgsConstructor;
import org.example.bank.constant.APIURL;
import org.example.bank.dto.request.UserProfileRequest;
import org.example.bank.dto.response.UserProfileResponse;
import org.example.bank.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(APIURL.USER_API)
@RequiredArgsConstructor
public class UserController {

    private final UserProfileService userProfileService;

    @GetMapping
    public UserProfileResponse getAllUserProfile () {
        return userProfileService.getAllUserProfile();
    }

    @PutMapping
    public ResponseEntity<UserProfileResponse> updateUserProfile (@RequestBody UserProfileRequest request) {
        UserProfileResponse updatedUserProfile = userProfileService.updateUserProfile(request);
        return ResponseEntity.ok(updatedUserProfile);
    }
}
