package com.management.room.business.responses.roomtype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdRoomTypeResponse {
	
	private Long id;
	
	private String name;
	
	private Long roomTypeGroupId;
	
	private String roomTypeGroupName;
	
}
