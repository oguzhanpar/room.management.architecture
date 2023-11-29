package com.management.room.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.management.room.business.abstracts.RoomStatusService;
import com.management.room.business.responses.roomstatus.GetAllRoomStatusResponse;
import com.management.room.core.utilities.mappers.ModelMapperService;
import com.management.room.dataAccess.abstracts.RoomStatusRepository;
import com.management.room.entities.concretes.RoomStatus;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoomStatusManager implements RoomStatusService {

	private RoomStatusRepository roomStatusRepository;
	
	private ModelMapperService modelMapperService;

	@Override
	public List<GetAllRoomStatusResponse> getAll() {
		
		
		List<RoomStatus> roomStatuses = roomStatusRepository.findAll();
		
		List<GetAllRoomStatusResponse> roomStatusesResponse = roomStatuses.stream()
				.map(roomStatus->this.modelMapperService.forResponse()
						.map(roomStatus, GetAllRoomStatusResponse.class)).collect(Collectors.toList());
		
		return roomStatusesResponse;
		
	}


	
}
