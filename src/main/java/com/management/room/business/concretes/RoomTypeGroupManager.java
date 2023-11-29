package com.management.room.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.management.room.business.abstracts.RoomTypeGroupService;
import com.management.room.business.requests.roomtypegroup.CreateRoomTypeGroupRequest;
import com.management.room.business.requests.roomtypegroup.UpdateRoomTypeGroupRequest;
import com.management.room.business.responses.roomtypegroup.GetAllRoomTypeGroupResponse;
import com.management.room.business.responses.roomtypegroup.GetByIdRoomTypeGroupResponse;
import com.management.room.core.utilities.exceptions.BusinessException;
import com.management.room.core.utilities.mappers.ModelMapperService;
import com.management.room.dataAccess.abstracts.RoomTypeGroupRepository;
import com.management.room.entities.concretes.RoomTypeGroup;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoomTypeGroupManager implements RoomTypeGroupService {

	private RoomTypeGroupRepository roomTypeGroupRepository;
	
	private ModelMapperService modelMapperService;

	@Override
	public Page<GetAllRoomTypeGroupResponse> getAll(Pageable pageable) {
	    
		Page<RoomTypeGroup> roomTypeGroupsPage = roomTypeGroupRepository.findAll(pageable);

	    Page<GetAllRoomTypeGroupResponse> roomTypeGroupsResponsePage = roomTypeGroupsPage.map(roomTypeGroup -> this.modelMapperService.forResponse()
	            .map(roomTypeGroup, GetAllRoomTypeGroupResponse.class));

	    return roomTypeGroupsResponsePage;
	}
	
	@Override
	public List<GetAllRoomTypeGroupResponse> getAllWithoutPaging() {
		
	    List<RoomTypeGroup> roomTypeGroups = roomTypeGroupRepository.findAll();

	    List<GetAllRoomTypeGroupResponse> roomTypeGroupResponses = roomTypeGroups.stream()
	            .map(roomTypeGroup -> modelMapperService.forResponse().map(roomTypeGroup, GetAllRoomTypeGroupResponse.class))
	            .collect(Collectors.toList());

	    return roomTypeGroupResponses;
	}

	@Override
	public void add(CreateRoomTypeGroupRequest createRoomTypeGroupRequest) {
		
		RoomTypeGroup roomTypeGroup = this.modelMapperService
				.forRequest()
				.map(createRoomTypeGroupRequest, RoomTypeGroup.class);
		this.roomTypeGroupRepository.save(roomTypeGroup);
		
	}
	
	@Override
	public GetByIdRoomTypeGroupResponse getById(Long id) {
		
		RoomTypeGroup roomTypeGroup = this.roomTypeGroupRepository.findById(id).orElseThrow();
		
		GetByIdRoomTypeGroupResponse response = 
				this.modelMapperService
				.forResponse()
				.map(roomTypeGroup, GetByIdRoomTypeGroupResponse.class);
		
		return response;
	}

	@Override
	public void delete(Long id) {
		
		this.roomTypeGroupRepository.deleteById(id);
		
	}

	@Override
	public void update(@Valid UpdateRoomTypeGroupRequest updateRoomTypeGroupRequest) {
		
		RoomTypeGroup roomTypeGroup = this.roomTypeGroupRepository.findById(updateRoomTypeGroupRequest.getId()).orElseThrow(() -> new BusinessException("Kayıt Bulunamadı!"));
		
		roomTypeGroup.setName(updateRoomTypeGroupRequest.getName());
		
		this.roomTypeGroupRepository.save(roomTypeGroup);

		
	}


	
}
