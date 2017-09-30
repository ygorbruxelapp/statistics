package com.statistics.controllers;

import com.statistics.domain.Statistics;
import com.statistics.repositories.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @RequestMapping(method = GET)
    public Statistics get() {
        return statisticsRepository.get();
    }
}
