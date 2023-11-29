package com.management.room.webApi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.management.room.business.abstracts.RoomTypeGroupService;
import com.management.room.business.requests.roomtypegroup.CreateRoomTypeGroupRequest;
import com.management.room.business.requests.roomtypegroup.UpdateRoomTypeGroupRequest;
import com.management.room.business.responses.roomtypegroup.GetAllRoomTypeGroupResponse;
import com.management.room.business.responses.roomtypegroup.GetByIdRoomTypeGroupResponse;


@RestController
@RequestMapping("api/v1/roomtypegroups")
public class RoomTypeGroupController {
	
	private RoomTypeGroupService roomTypeGroupService;

	@Autowired
	public RoomTypeGroupController(RoomTypeGroupService roomTypeGroupService) {
		this.roomTypeGroupService = roomTypeGroupService;
	}
	
	@GetMapping
	public Page<GetAllRoomTypeGroupResponse> getAll(Pageable pageable){
		return roomTypeGroupService.getAll(pageable);
	}
	
	@GetMapping("/withoutpaging")
	public List<GetAllRoomTypeGroupResponse> getAllWithoutPaging(){
		return roomTypeGroupService.getAllWithoutPaging();
	}
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public void add(@RequestBody @Valid CreateRoomTypeGroupRequest createRoomTypeGroupRequest) {
		this.roomTypeGroupService.add(createRoomTypeGroupRequest);
	}
	
	@PutMapping
	public void update(@RequestBody @Valid UpdateRoomTypeGroupRequest updateRoomTypeGroupRequest) {
		this.roomTypeGroupService.update(updateRoomTypeGroupRequest);
	}
	
	@GetMapping("/{id}")
	public GetByIdRoomTypeGroupResponse getById(@PathVariable Long id) {
		return roomTypeGroupService.getById(id);		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		this.roomTypeGroupService.delete(id);
	}

}
