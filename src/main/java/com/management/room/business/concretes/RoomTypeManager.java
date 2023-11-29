package com.management.room.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.management.room.business.abstracts.RoomTypeService;
import com.management.room.business.requests.roomtype.CreateRoomTypeRequest;
import com.management.room.business.requests.roomtype.UpdateRoomTypeRequest;
import com.management.room.business.responses.roomtype.GetAllRoomTypeResponse;
import com.management.room.business.responses.roomtype.GetByIdRoomTypeResponse;
import com.management.room.core.utilities.exceptions.BusinessException;
import com.management.room.core.utilities.mappers.ModelMapperService;
import com.management.room.dataAccess.abstracts.RoomTypeGroupRepository;
import com.management.room.dataAccess.abstracts.RoomTypeRepository;
import com.management.room.entities.concretes.RoomType;
import com.management.room.entities.concretes.RoomTypeGroup;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoomTypeManager implements RoomTypeService {

	private RoomTypeRepository roomTypeRepository;
	private RoomTypeGroupRepository roomTypeGroupRepository;
	
	private ModelMapperService modelMapperService;

	@Override
	public Page<GetAllRoomTypeResponse> getAll(Pageable pageable) {
		
		Page<RoomType> roomTypePage = roomTypeRepository.findAll(pageable);

	    Page<GetAllRoomTypeResponse> roomTypeResponsePage = roomTypePage.map(roomType -> this.modelMapperService.forResponse()
	            .map(roomType, GetAllRoomTypeResponse.class));

	    return roomTypeResponsePage;
		
	}
	
	@Override
	public List<GetAllRoomTypeResponse> getAllWithoutPaging() {
	    List<RoomType> roomTypes = roomTypeRepository.findAll();

	    List<GetAllRoomTypeResponse> roomTypeResponses = roomTypes.stream()
	            .map(roomType -> modelMapperService.forResponse().map(roomType, GetAllRoomTypeResponse.class))
	            .collect(Collectors.toList());

	    return roomTypeResponses;
	}



	@Override
	public GetByIdRoomTypeResponse getById(Long id) {
		
		RoomType roomType = this.roomTypeRepository.findById(id).orElseThrow();
		
		GetByIdRoomTypeResponse response = 
				this.modelMapperService
				.forResponse()
				.map(roomType, GetByIdRoomTypeResponse.class);
		
		return response;
	}


	@Override
	public void add(CreateRoomTypeRequest createRoomTypeRequest) {
		
		RoomType roomType = this.modelMapperService
				.forRequest()
				.map(createRoomTypeRequest, RoomType.class);
		
		roomType.setId(null);
		
		this.roomTypeRepository.save(roomType);
		
	}
	
	@Override
	public void delete(Long id) {
		
		this.roomTypeRepository.deleteById(id);
		
	}

	@Override
	public void update(@Valid UpdateRoomTypeRequest updateRoomTypeRequest) {
		
		RoomType roomType = this.roomTypeRepository.findById(updateRoomTypeRequest.getId()).orElseThrow(() -> new BusinessException("Kayıt Bulunamadı!"));
		
		roomType.setName(updateRoomTypeRequest.getName());
		
		RoomTypeGroup roomTypeGroup = this.roomTypeGroupRepository.findById(updateRoomTypeRequest.getRoomTypeGroupId()).orElseThrow(() -> new BusinessException("Kayıt Bulunamadı!"));

		
		roomType.setRoomTypeGroup(roomTypeGroup);

		
		this.roomTypeRepository.save(roomType);
		
	}

	
}
