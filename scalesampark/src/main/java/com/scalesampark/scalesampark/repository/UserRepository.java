package com.scalesampark.scalesampark.repository;

import com.scalesampark.scalesampark.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface UserRepository extends JpaRepository<User, String> {
}
