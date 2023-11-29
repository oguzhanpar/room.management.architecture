package com.management.room.business.requests.room;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoomRequest {
		
	@NotNull
	private Long id;
	
	@NotBlank
	@NotNull
	@Size(min=2)
	private String number;
	
	@NotNull
	private Long roomTypeId;
		
	@NotNull
	private Long roomStatusId;

}
