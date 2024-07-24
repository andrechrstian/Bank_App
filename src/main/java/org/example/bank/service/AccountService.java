package org.example.bank.service;

import org.example.bank.dto.response.AccountResponse;
import org.example.bank.entity.Account;
import org.example.bank.entity.Transaction;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    Account getAccountByNumber(String accountNumber);
    List<AccountResponse> getAllAccount ();
    void deleteAccount(String id);
    void deposit(String accountNumber, Double amount);
    void withdraw(String accountNumber, Double amount);
}
