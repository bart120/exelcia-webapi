package com.exelcia.webapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exelcia.webapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findUserByUsername(String username);

	Boolean existsByUsername(String username);

}
