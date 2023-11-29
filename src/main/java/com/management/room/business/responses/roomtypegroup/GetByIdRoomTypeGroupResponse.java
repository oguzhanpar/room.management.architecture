package com.management.room.business.responses.roomtypegroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdRoomTypeGroupResponse {
	
	private Long id;
	private String name;
	
}
