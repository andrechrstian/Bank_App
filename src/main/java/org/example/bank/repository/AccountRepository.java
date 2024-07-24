package org.example.bank.repository;

import org.example.bank.entity.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    Account findByAccountNumber(String accountNumber);

    @Query(value = "SELECT * FROM m_account", nativeQuery = true)
    List<Account> findAllAccount();

}
