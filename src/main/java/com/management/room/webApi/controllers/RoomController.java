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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.management.room.business.abstracts.RoomService;
import com.management.room.business.requests.room.CreateRoomRequest;
import com.management.room.business.requests.room.UpdateRoomRequest;
import com.management.room.business.responses.room.GetAllRoomResponse;
import com.management.room.business.responses.room.GetByIdRoomResponse;

@RestController
@RequestMapping("api/v1/rooms")
public class RoomController {
	
	private RoomService roomService;

	@Autowired
	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}
	
	@GetMapping("/withoutpaging")
	public List<GetAllRoomResponse> getAllWithoutPaging(){
		return roomService.getAllWithoutPaging();
	}
	
	@GetMapping
	public Page<GetAllRoomResponse> getAll(
			@RequestParam(required = false) String keyword,
			@RequestParam(defaultValue = "id") String orderBy,
		    @RequestParam(defaultValue = "desc") String orderDirection,
		    Pageable paging) {

	    return roomService.getAll(keyword, orderBy, orderDirection, paging);
	}

	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public void add(@RequestBody @Valid CreateRoomRequest createRoomRequest) {
		this.roomService.add(createRoomRequest);
	}
	
	@GetMapping("/{id}")
	public GetByIdRoomResponse getById(@PathVariable Long id) {
		return roomService.getById(id);		
	}
	
	@PutMapping
	public void update(@RequestBody @Valid UpdateRoomRequest updateRoomRequest) {
		this.roomService.update(updateRoomRequest);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		this.roomService.delete(id);
	}

}
