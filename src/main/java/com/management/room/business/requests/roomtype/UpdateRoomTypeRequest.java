package com.management.room.business.requests.roomtype;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoomTypeRequest {
	
	@NotNull
	Long id;
	
	@NotBlank
	@NotNull
	@Size(min=2)
	String name;
	
	@NotNull
	Long roomTypeGroupId;

}
