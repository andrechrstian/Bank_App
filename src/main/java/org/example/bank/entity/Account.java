package org.example.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String accountNumber;
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;
}
