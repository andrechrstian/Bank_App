package org.example.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.bank.dto.response.AccountResponse;
import org.example.bank.entity.Account;
import org.example.bank.entity.Transaction;
import org.example.bank.repository.AccountRepository;
import org.example.bank.repository.TransactionRepository;
import org.example.bank.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;


    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountResponse> getAllAccount() {
        return accountRepository.findAllAccount().stream()
                .map(this::convertToAccountResponse)
                .toList();
    }

    @Override
    public void deposit(String accountNumber, Double amount) {
        Account account = getAccountByNumber(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            accountRepository.save(account);

            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setAmount(amount);
            transaction.setType("DEPOSIT");
            transaction.setTransactionDate(new Date());
            transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    @Override
    public void withdraw(String accountNumber, Double amount) {
        Account account = getAccountByNumber(accountNumber);
        if (account != null) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);

                Transaction transaction = new Transaction();
                transaction.setAccount(account);
                transaction.setAmount(amount);
                transaction.setType("WITHDRAWAL");
                transaction.setTransactionDate(new Date());
                transactionRepository.save(transaction);
            } else {
                throw new RuntimeException("Insufficient funds");
            }
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    private AccountResponse convertToAccountResponse (Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .build();
    }
}
