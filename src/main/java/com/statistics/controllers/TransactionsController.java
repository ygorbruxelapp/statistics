package com.statistics.controllers;


import com.statistics.domain.Transaction;
import com.statistics.exceptions.ExpiredTransactionException;
import com.statistics.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController("/transactions")
public class TransactionsController {

    @Autowired
    private TransactionRepository transactionRepository;

    @RequestMapping(method = POST)
    public ResponseEntity<?> create(@RequestBody Transaction transaction) {
        if (transaction.isExpired(ZonedDateTime.now(ZoneOffset.UTC))) {
            throw new ExpiredTransactionException();
        }

        transactionRepository.create(transaction);

        return ResponseEntity.created(URI.create("")).build();
    }
}
