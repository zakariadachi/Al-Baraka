package com.albaraka.service;

import com.albaraka.dao.TransactionDAO;
import com.albaraka.entity.Transaction;

import java.util.List;

public class TransactionService {
    private final TransactionDAO transactionDAO;

    public TransactionService() {
        this.transactionDAO = new TransactionDAO();
    }

    public void createTransaction(Transaction transaction) {
        transactionDAO.save(transaction);
    }

    public List<Transaction> getTransactionsByCompteId(int compteId) {
        return transactionDAO.findByCompteId(compteId);
    }

    public List<Transaction> getAllTransactions() {
        return transactionDAO.findAll();
    }
}
