package com.management.room.dataAccess.abstracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.room.entities.concretes.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	
	Page<Room> findAllByNumberContainingIgnoreCase(String keyword, Pageable pageable);
	

}
