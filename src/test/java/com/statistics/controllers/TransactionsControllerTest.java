package com.statistics.controllers;

import com.statistics.repositories.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.statistics.factory.TimestampFactory.getTimestampsSecondsAgo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionsController.class)
public class TransactionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    public void addingTransactionsShouldReturnCreatedFromService() throws Exception {
        this.mockMvc.perform(
                post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\": 12.3,\"timestamp\": " + getTimestampsSecondsAgo(50) + "}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void addingExpiredTransactionsShouldReturnNoContentFromService() throws Exception {
        this.mockMvc.perform(
                post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\": 12.3,\"timestamp\": " + getTimestampsSecondsAgo(70) + "}"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void createTransactionInRepositoryWhenItsValid() throws Exception {
        this.mockMvc.perform(
                post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\": 12.3,\"timestamp\": " + getTimestampsSecondsAgo(50) + "}"))
                .andExpect(status().isCreated());

        verify(transactionRepository, times(1)).create(any());
    }
}
