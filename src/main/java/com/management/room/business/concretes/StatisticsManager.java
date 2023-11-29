package com.management.room.business.concretes;


import org.springframework.stereotype.Service;

import com.management.room.business.abstracts.StatisticsService;
import com.management.room.business.responses.statistics.DashboardStatisticsResponse;
import com.management.room.dataAccess.abstracts.RoomRepository;
import com.management.room.dataAccess.abstracts.RoomTypeGroupRepository;
import com.management.room.dataAccess.abstracts.RoomTypeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatisticsManager implements StatisticsService{
	

	private RoomRepository roomRepository;
	private RoomTypeRepository roomTypeRepository;
	private RoomTypeGroupRepository roomTypeGroupRepository;
	
	@Override
	public DashboardStatisticsResponse getAllDashboardStatistics(){
		
		DashboardStatisticsResponse dashboardStatisticsResponse = new DashboardStatisticsResponse();
		dashboardStatisticsResponse.setCountRoom(roomRepository.count());
		dashboardStatisticsResponse.setCountRoomType(roomTypeRepository.count());
		dashboardStatisticsResponse.setCountRoomTypeGroup(roomTypeGroupRepository.count());
		
		return dashboardStatisticsResponse;
	
	}

}
