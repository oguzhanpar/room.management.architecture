package com.management.room.business.responses.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardStatisticsResponse {
	
	Long countRoomTypeGroup;
	
	Long countRoomType;

	Long countRoom;

}
