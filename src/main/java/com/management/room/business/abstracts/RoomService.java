package com.management.room.business.abstracts;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.management.room.business.requests.room.CreateRoomRequest;
import com.management.room.business.requests.room.UpdateRoomRequest;
import com.management.room.business.responses.room.GetAllRoomResponse;
import com.management.room.business.responses.room.GetByIdRoomResponse;

public interface RoomService {
	
	Page<GetAllRoomResponse> getAll(String keyword, String orderBy, String orderDirection, Pageable paging);
	
	List<GetAllRoomResponse> getAllWithoutPaging();
	
	GetByIdRoomResponse getById(Long id);

	void add(CreateRoomRequest createRoomRequest);

	void delete(Long id);
	
	void update(@Valid UpdateRoomRequest updateRoomRequest);
	
}
