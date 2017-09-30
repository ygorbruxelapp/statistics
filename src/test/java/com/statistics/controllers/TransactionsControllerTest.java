package com.statistics.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.statistics.factory.TimestampFactory.getTimestampsSecondsAgo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionsController.class)
public class TransactionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addingTransactionsShouldReturnCreatedFromService() throws Exception {
        this.mockMvc.perform(
                post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\": 12.3,\"timestamp\": " + getTimestampsSecondsAgo(50) + "}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
