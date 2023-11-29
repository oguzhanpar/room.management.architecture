package com.management.room.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;

import com.management.room.entities.concretes.RoomType;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {

}
