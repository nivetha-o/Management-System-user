package com.java.springboot_with_vuejs.repository;

import com.java.springboot_with_vuejs.entity.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTagRepository extends JpaRepository<UserTag, Long> {
}
