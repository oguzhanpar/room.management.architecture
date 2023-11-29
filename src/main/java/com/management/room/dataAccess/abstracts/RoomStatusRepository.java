package com.management.room.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.room.entities.concretes.RoomStatus;

public interface RoomStatusRepository extends JpaRepository<RoomStatus, Long> {

}
