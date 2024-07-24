package org.example.bank.repository;

import org.example.bank.entity.Account;
import org.example.bank.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String>, JpaSpecificationExecutor<UserProfile> {
    @Query(value = "SELECT * FROM m_customer", nativeQuery = true)
    UserProfile findAllUserProfile();
}
