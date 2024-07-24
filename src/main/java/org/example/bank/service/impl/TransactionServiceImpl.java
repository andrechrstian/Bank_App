package org.example.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.bank.dto.response.TransactionResponse;
import org.example.bank.entity.Transaction;
import org.example.bank.repository.TransactionRepository;
import org.example.bank.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionResponse> getAllTransaction(String id) {
        return transactionRepository.getAllTransaction(id).stream()
                .map(this::convertToTrxResponse)
                .toList();
    }

    private TransactionResponse convertToTrxResponse (Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .transactionDate(transaction.getTransactionDate())
                .build();
    }
}
