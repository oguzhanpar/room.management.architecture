package com.management.room.business.abstracts;

import java.util.List;

import com.management.room.business.responses.roomstatus.GetAllRoomStatusResponse;

public interface RoomStatusService {
	
	List<GetAllRoomStatusResponse> getAll();

}
