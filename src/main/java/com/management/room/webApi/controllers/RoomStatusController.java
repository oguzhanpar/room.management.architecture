package com.management.room.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.room.business.abstracts.RoomStatusService;
import com.management.room.business.responses.roomstatus.GetAllRoomStatusResponse;


@RestController
@RequestMapping("api/v1/roomstatus")
public class RoomStatusController {
	
	private RoomStatusService roomStatusService;

	@Autowired
	public RoomStatusController(RoomStatusService roomStatusService) {
		this.roomStatusService = roomStatusService;
	}
	
	@GetMapping
	public List<GetAllRoomStatusResponse> getAll(){
		return roomStatusService.getAll();
	}
	

}
