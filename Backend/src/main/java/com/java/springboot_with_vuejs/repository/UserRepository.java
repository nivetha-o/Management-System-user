package com.java.springboot_with_vuejs.repository;

import com.java.springboot_with_vuejs.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByDeletedFalse();
}
