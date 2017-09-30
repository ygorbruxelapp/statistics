package com.statistics.controllers;


import com.statistics.domain.Transaction;
import com.statistics.exceptions.ExpiredTransactionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController("/transactions")
public class TransactionsController {

    @RequestMapping(method = POST)
    public ResponseEntity<?> create(@RequestBody Transaction transaction) {
        if (transaction.isExpired()) {
            throw new ExpiredTransactionException();
        }
        return ResponseEntity.created(URI.create("")).build();
    }
}
