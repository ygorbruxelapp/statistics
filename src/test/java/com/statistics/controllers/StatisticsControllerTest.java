package com.statistics.controllers;

import com.statistics.domain.Statistics;
import com.statistics.repositories.StatisticsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StatisticsController.class)
public class StatisticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatisticsRepository statisticsRepository;

    @Test
    public void fetchStatistics() throws Exception {
        when(statisticsRepository.get()).thenReturn(new Statistics(1, 2, 3, 4, 5));
        this.mockMvc.perform(
                get("/statistics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sum", is(1.0)))
                .andExpect(jsonPath("$.avg", is(2.0)))
                .andExpect(jsonPath("$.max", is(3.0)))
                .andExpect(jsonPath("$.min", is(4.0)))
                .andExpect(jsonPath("$.count", is(5)));
    }
}
