package com.management.room.business.abstracts;

import java.util.Optional;

import com.management.room.entities.concretes.User;

public interface UserService {
	
    Optional<User> findByUsername(String username);

}
