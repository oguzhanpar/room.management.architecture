package com.management.room.business.abstracts;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.management.room.business.requests.roomtype.CreateRoomTypeRequest;
import com.management.room.business.requests.roomtype.UpdateRoomTypeRequest;
import com.management.room.business.responses.roomtype.GetAllRoomTypeResponse;
import com.management.room.business.responses.roomtype.GetByIdRoomTypeResponse;

public interface RoomTypeService {
	
	List<GetAllRoomTypeResponse> getAllWithoutPaging();
	
	Page<GetAllRoomTypeResponse> getAll(Pageable pageable);
	
	GetByIdRoomTypeResponse getById(Long id);

	void add(CreateRoomTypeRequest createRoomTypeRequest);

	void update(@Valid UpdateRoomTypeRequest updateRoomTypeRequest);

	void delete(Long id);

	
}
