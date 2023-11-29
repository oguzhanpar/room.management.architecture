package com.management.room.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.management.room.business.abstracts.EmailService;
import com.management.room.business.abstracts.RoomService;
import com.management.room.business.requests.email.EmailDetails;
import com.management.room.business.requests.room.CreateRoomRequest;
import com.management.room.business.requests.room.UpdateRoomRequest;
import com.management.room.business.responses.room.GetAllRoomResponse;
import com.management.room.business.responses.room.GetByIdRoomResponse;
import com.management.room.core.utilities.exceptions.BusinessException;
import com.management.room.core.utilities.mappers.ModelMapperService;
import com.management.room.dataAccess.abstracts.RoomRepository;
import com.management.room.dataAccess.abstracts.RoomStatusRepository;
import com.management.room.dataAccess.abstracts.RoomTypeRepository;
import com.management.room.entities.concretes.Room;
import com.management.room.entities.concretes.RoomStatus;
import com.management.room.entities.concretes.RoomType;

import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoomManager implements RoomService {

	private RoomRepository roomRepository;
	
	private RoomTypeRepository roomTypeRepository;
	
	private RoomStatusRepository roomStatusRepository;
	
	private ModelMapperService modelMapperService;
	
	@Autowired
	@Qualifier("ethereal")
	private EmailService emailService;

	@Override
	public Page<GetAllRoomResponse> getAll(String keyword, String orderBy, String orderDirection, Pageable paging) {
	    Pageable sortedPageable;
	    if (paging.getSort().isSorted()) {
	        sortedPageable = paging;
	    } else {
	        if (orderBy == null || orderBy.isEmpty()) {
	            orderBy = "defaultSortField";
	        }
	        
	        Sort sort = Sort.by(orderDirection.equals("desc") ? Sort.Order.desc(orderBy) : Sort.Order.asc(orderBy));
	        sortedPageable = PageRequest.of(paging.getPageNumber(), paging.getPageSize(), sort);
	    }
	    
	    Page<Room> roomPage = roomRepository.findAllByNumberContainingIgnoreCase(keyword, sortedPageable);

	    List<GetAllRoomResponse> roomsResponseList = roomPage
	            .stream()
	            .map(room -> {
	                GetAllRoomResponse response = modelMapperService.forResponse().map(room, GetAllRoomResponse.class);

	                response.setRoomTypeGroupId(room.getRoomType().getRoomTypeGroup().getId());
	                response.setRoomTypeGroupName(room.getRoomType().getRoomTypeGroup().getName());
	                return response;
	            })
	            .collect(Collectors.toList());

	    return new PageImpl<>(roomsResponseList, sortedPageable, roomPage.getTotalElements());
	}


	
	@Override
	public List<GetAllRoomResponse> getAllWithoutPaging() {
		
		  List<Room> rooms = roomRepository.findAll();
		    
		  List<GetAllRoomResponse> roomsResponse = rooms.stream()
		            .map(room -> {
		                GetAllRoomResponse response = modelMapperService.forResponse().map(room, GetAllRoomResponse.class);
		                response.setRoomTypeGroupId(room.getRoomType().getRoomTypeGroup().getId());
		                response.setRoomTypeGroupName(room.getRoomType().getRoomTypeGroup().getName());
		                return response;
		            })
		            .collect(Collectors.toList());
		    
		    return roomsResponse;
		
	}
	
	@Override
	public void add(CreateRoomRequest createRoomRequest) {
		
		Room room = this.modelMapperService
				.forRequest()
				.map(createRoomRequest, Room.class);
		this.roomRepository.save(room);
		
		EmailDetails emailDetails = EmailDetails.builder()
				.to("roommanagementtest@yahoo.com")
				.subject("Yeni Oda Kaydı")
				.body(String.format("%s isimli oda sisteme kaydedildi.",
						createRoomRequest.getNumber()))
				.build();
		
		emailService.send(emailDetails);
		System.out.println("Mail Gönderildi2132132!");

		
	}

	
	@Override
	public GetByIdRoomResponse getById(Long id) {
		
		Room room = this.roomRepository.findById(id).orElseThrow(() -> new BusinessException("Veri Bulunamadı!"));
		
		GetByIdRoomResponse response = 
				this.modelMapperService
				.forResponse()
				.map(room, GetByIdRoomResponse.class);
		
		return response;
		
	}

	@Override
	public void delete(Long id) {
		this.roomRepository.deleteById(id);	
	}



	@Override
	public void update(@Valid UpdateRoomRequest updateRoomRequest) {
		
		Room room = this.roomRepository.findById(updateRoomRequest.getId()).orElseThrow(() -> new BusinessException("Kayıt Bulunamadı!"));
		RoomType roomType = this.roomTypeRepository.findById(updateRoomRequest.getRoomTypeId()).orElseThrow(() -> new BusinessException("RoomType Bulunamadı!"));
		RoomStatus roomStatus = this.roomStatusRepository.findById(updateRoomRequest.getRoomStatusId()).orElseThrow(() -> new BusinessException("Room Status Bulunamadı!"));
		
		room.setNumber(updateRoomRequest.getNumber());
		room.setRoomType(roomType);
		room.setRoomStatus(roomStatus);
		
		this.roomRepository.save(room);
		
	}




	
}
