package com.statistics.endtoend;

import com.statistics.domain.Statistics;
import com.statistics.domain.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.concurrent.*;

import static junit.framework.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndToEndTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        postTransactionWithAmount(100);
        postTransactionWithAmount(300);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Statistics> future = executor.submit(tryUntilProcessed());

        Statistics statistics = future.get(3, TimeUnit.SECONDS);

        assertEquals(400d, statistics.getSum());
        assertEquals(200d, statistics.getAvg());
        assertEquals(300d, statistics.getMax());
        assertEquals(100d, statistics.getMin());
        assertEquals(2, statistics.getCount());
    }

    private void postTransactionWithAmount(int amount) {
        Transaction transaction = new Transaction();
        transaction.setTimestamp(ZonedDateTime.now(ZoneOffset.UTC).toEpochSecond() * 1000);
        transaction.setAmount(amount);

        this.restTemplate.postForEntity("http://localhost:" + port + "/", transaction, null);
    }

    private Callable<Statistics> tryUntilProcessed() {
        return () -> {
            try {
                while (true) {
                    Statistics statistics = this.restTemplate.getForObject("http://localhost:" + port + "/",
                            Statistics.class);

                    if (statistics.getSum() > 0) {
                        return statistics;
                    }
                    TimeUnit.MILLISECONDS.sleep(500);
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };
    }
}
