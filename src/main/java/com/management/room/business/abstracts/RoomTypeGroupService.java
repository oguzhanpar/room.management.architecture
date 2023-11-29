package com.management.room.business.abstracts;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.management.room.business.requests.roomtypegroup.CreateRoomTypeGroupRequest;
import com.management.room.business.requests.roomtypegroup.UpdateRoomTypeGroupRequest;
import com.management.room.business.responses.roomtypegroup.GetAllRoomTypeGroupResponse;
import com.management.room.business.responses.roomtypegroup.GetByIdRoomTypeGroupResponse;

public interface RoomTypeGroupService {
	
	List<GetAllRoomTypeGroupResponse> getAllWithoutPaging();
	
    Page<GetAllRoomTypeGroupResponse> getAll(Pageable pageable);

	void add(CreateRoomTypeGroupRequest createRoomTypeGroupRequest);

	GetByIdRoomTypeGroupResponse getById(Long id);

	void delete(Long id);

	void update(@Valid UpdateRoomTypeGroupRequest updateRoomTypeGroupRequest);

}