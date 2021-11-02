package com.epam.mvc.repository;

import com.epam.mvc.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByUserName(String userName);
}
