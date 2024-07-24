package org.example.bank.controller;

import lombok.RequiredArgsConstructor;
import org.example.bank.constant.APIURL;
import org.example.bank.dto.request.AccountRequest;
import org.example.bank.dto.response.AccountResponse;
import org.example.bank.dto.response.TransactionResponse;
import org.example.bank.entity.Account;
import org.example.bank.entity.Transaction;
import org.example.bank.service.AccountService;
import org.example.bank.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIURL.ACC_API)
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody Account request) {
        accountService.createAccount(request);
        return ResponseEntity.ok("Account Created");
    }

   @PostMapping("/deposit")
   public ResponseEntity<String>  deposit (@RequestHeader String accountNumber, @RequestBody Double amount) {
        accountService.deposit(accountNumber,amount);
        return ResponseEntity.ok("DEPOSIT SUCCESSFULLY");
   }

   @PostMapping("/withdraw")
   public ResponseEntity<String> withdraw (@RequestHeader String accountNumber, @RequestBody Double amount) {
        accountService.withdraw(accountNumber,amount);
        return ResponseEntity.ok("WITHDRAW SUCCESSFULLY");
   }

   @DeleteMapping
   public ResponseEntity<String> deleteAccount (@RequestParam String id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account Deleted");
   }

   @GetMapping
   public List<AccountResponse> getAllAccount () {
        return accountService.getAllAccount();
   }

   @GetMapping("/transaction")
   public List<TransactionResponse> getAllTransaction (@RequestParam String id) {
        return transactionService.getAllTransaction(id);
   }

}
