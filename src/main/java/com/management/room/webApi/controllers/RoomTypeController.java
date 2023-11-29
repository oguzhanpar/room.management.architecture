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

import com.management.room.business.abstracts.RoomTypeService;
import com.management.room.business.requests.roomtype.CreateRoomTypeRequest;
import com.management.room.business.requests.roomtype.UpdateRoomTypeRequest;
import com.management.room.business.responses.roomtype.GetAllRoomTypeResponse;
import com.management.room.business.responses.roomtype.GetByIdRoomTypeResponse;


@RestController
@RequestMapping("api/v1/roomtypes")
public class RoomTypeController {
	
	private RoomTypeService roomTypeService;

	@Autowired
	public RoomTypeController(RoomTypeService roomTypeService) {
		this.roomTypeService = roomTypeService;
	}
	
	@GetMapping
	public Page<GetAllRoomTypeResponse> getAll(Pageable pageable){
		return roomTypeService.getAll(pageable);
	}
	
	@GetMapping("/withoutpaging")
	public List<GetAllRoomTypeResponse> getAllWithoutPaging(){
		return roomTypeService.getAllWithoutPaging();
	}
	
	@GetMapping("/{id}")
	public GetByIdRoomTypeResponse getById(@PathVariable Long id) {
		return roomTypeService.getById(id);		
	}
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public void add(@RequestBody @Valid CreateRoomTypeRequest createRoomTypeRequest) {
		this.roomTypeService.add(createRoomTypeRequest);
	}
	
	@PutMapping
	public void update(@RequestBody @Valid UpdateRoomTypeRequest updateRoomTypeRequest) {
		this.roomTypeService.update(updateRoomTypeRequest);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		this.roomTypeService.delete(id);
	}

}
