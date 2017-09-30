package com.statistics.tasks;

import com.statistics.domain.Transaction;
import com.statistics.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class RemoveExpiredTransactionsTask {

    private TransactionRepository transactionRepository;

    @Autowired
    public RemoveExpiredTransactionsTask(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Scheduled(fixedRate = 1000)
    public void updateStatistics() {
        List<Transaction> all = transactionRepository.findAll();
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        all.stream()
                .filter(t -> t.isExpired(now))
                .forEach(transactionRepository::delete);
    }
}
