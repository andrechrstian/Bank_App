package org.example.bank.repository;

import org.example.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    @Query(value = "SELECT * FROM m_transaction WHERE account_id = :accountId", nativeQuery = true)
    List<Transaction> getAllTransaction(@Param("accountId")  String accountId);
}
