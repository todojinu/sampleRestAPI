package com.example.sampleRestAPI.repository;

import com.example.sampleRestAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
