package com.management.room.webApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.room.business.abstracts.StatisticsService;
import com.management.room.business.responses.statistics.DashboardStatisticsResponse;

@RestController
@RequestMapping("api/v1/statistics")
public class StatisticsController {
	
	private StatisticsService statisticsService;

	@Autowired
	public StatisticsController(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}
	
	@GetMapping("/dashboard")
	public DashboardStatisticsResponse getAll(){
		return statisticsService.getAllDashboardStatistics();
	}

}
