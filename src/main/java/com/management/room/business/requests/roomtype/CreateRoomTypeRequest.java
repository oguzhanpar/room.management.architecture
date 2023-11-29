package com.management.room.business.requests.roomtype;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoomTypeRequest {
	
	Long id;
	
	@NotBlank
	@NotNull
	String name;
	
	@NotNull
	Long roomTypeGroupId;

}