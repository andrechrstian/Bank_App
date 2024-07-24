package org.example.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "m_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;
    public String username;
    public String password;
}
