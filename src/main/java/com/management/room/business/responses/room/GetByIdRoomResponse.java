package com.management.room.business.responses.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdRoomResponse {
	
	private Long id;
	
	private String number;
	
	private Long roomTypeId;
	
	private String roomTypeName;
	
	private Long roomTypeGroupId;
	
	private String roomTypeGroupName;
	
	private Long roomStatusId;
	
	private String roomStatusName;
	
}
