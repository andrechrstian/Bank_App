package org.example.bank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequest {
     private Integer balance;
     private String userProfileId;
     private String accountNumber;
}
