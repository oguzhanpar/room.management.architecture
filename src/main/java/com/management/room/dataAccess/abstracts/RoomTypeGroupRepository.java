package com.management.room.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.room.entities.concretes.RoomTypeGroup;

@Repository
public interface RoomTypeGroupRepository extends JpaRepository<RoomTypeGroup,Long> {

}
