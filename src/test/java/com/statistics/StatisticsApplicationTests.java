package com.statistics;

import com.statistics.controllers.TransactionsController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatisticsApplicationTests {

    @Autowired
    private TransactionsController transactionsController;

    @Test
    public void contextLoads() {
        assertThat(transactionsController).isNotNull();
    }
}
