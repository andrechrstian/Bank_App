package org.example.bank.service;

import org.example.bank.dto.response.TransactionResponse;
import org.example.bank.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<TransactionResponse> getAllTransaction (String id);
}
