package com.management.room.dataAccess.concretes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.management.room.dataAccess.abstracts.RoomRepository;
import com.management.room.dataAccess.abstracts.RoomTypeGroupRepository;
import com.management.room.dataAccess.abstracts.RoomTypeRepository;
import com.management.room.entities.concretes.Room;
import com.management.room.entities.concretes.RoomType;
import com.management.room.entities.concretes.RoomTypeGroup;
import com.github.javafaker.Faker;

@Component
public class FakeDataLoader implements CommandLineRunner{
	

	@Override
	public void run(String... args) throws Exception {
		loadRoomTypeGroup();
	}
	
	@Autowired
	private Faker faker;
	@Autowired
	private RoomTypeGroupRepository roomTypeGroupRepository;
	@Autowired
	private RoomTypeRepository roomTypeRepository;
	@Autowired
	private RoomRepository roomRepository;
	
	

	private void loadRoomTypeGroup() {
		
		Long count = 0L;
		
		for (int i = 0; i < 10; i++) {
			count++;
			RoomTypeGroup rmGroup = newRoomTypeGroup(count);
			roomTypeGroupRepository.save(rmGroup);
			for (int k = 0; k < 10; k++) {
				RoomType rmType = newRoomType(rmGroup);
				roomTypeRepository.save(rmType); 
			
			for (int m = 0; m < 10; m++) {
				roomRepository.save(newRoom(rmType)); 
				}
					
			}
		}
		
	}
	
	private RoomTypeGroup newRoomTypeGroup(Long Count) {
		
		final String name = faker.country().capital();
		return RoomTypeGroup.builder()
				.id(null)
				.name(name)
				.build();
	}
	
	private RoomType newRoomType(RoomTypeGroup roomTypeGroup) {
		
		final String name = faker.ancient().hero();	
		return RoomType.builder()
				.name(name)
				.roomTypeGroup(roomTypeGroup)
				.build();
	}
	
	private Room newRoom(RoomType roomType) {
		
		
        String roomNumber = generateRoomNumber(faker);
        
		return Room.builder()
				.number(roomNumber)
				.roomType(roomType)
				.build();
	}

	
	public static String generateRoomNumber(Faker faker) {
        String roomPrefix = faker.letterify("?");
        int roomNumber = faker.number().numberBetween(1001, 9999);

        return roomPrefix + roomNumber;
    }


	


}
