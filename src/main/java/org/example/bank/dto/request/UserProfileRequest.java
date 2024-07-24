package org.example.bank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bank.entity.User;

import java.util.Date;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileRequest {

    private String id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    private User user;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AuthRequest<T> {
        private String username;
        private String password;

        private Optional<T> data;
    }
}
